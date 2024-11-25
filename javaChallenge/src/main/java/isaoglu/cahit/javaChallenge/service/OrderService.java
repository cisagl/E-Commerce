package isaoglu.cahit.javaChallenge.service;

import isaoglu.cahit.javaChallenge.dto.request.OrderRequestDTO;
import isaoglu.cahit.javaChallenge.dto.response.OrderResponseDTO;
import isaoglu.cahit.javaChallenge.entity.*;
import isaoglu.cahit.javaChallenge.repository.*;
import isaoglu.cahit.javaChallenge.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemHistoryRepository orderItemHistoryRepository;

    // Order nesnesi dtoya çevrildi
    private OrderResponseDTO convertToOrderResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setCode(order.getCode());
        responseDTO.setTotal(order.getTotal());
        responseDTO.setCustomerId(order.getCustomer().getId());
        responseDTO.setItems(order.getItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList()));
        return responseDTO;
    }

    // OrderItem nesnesi dtoya çevrildi
    private OrderResponseDTO.OrderItemDTO convertToOrderItemDTO(OrderItem item) {
        OrderResponseDTO.OrderItemDTO orderItemDTO = new OrderResponseDTO.OrderItemDTO();
        orderItemDTO.setProductName(item.getProductName());
        orderItemDTO.setPriceAtPurchase(item.getPriceAtPurchase());
        orderItemDTO.setQuantity(item.getQuantity());
        return orderItemDTO;
    }

    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        Cart cart = cartRepository.findById(orderRequestDTO.getCartId()).orElseThrow();

        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                throw new RuntimeException("Yetersiz stok");
            }
        }

        if (cart.getTotal() == 0) {
            throw new RuntimeException("Sepet boş");
        }

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setItems(new ArrayList<>());
        order.setTotal(cart.getTotal());
        order.setCode(CodeGenerator.generateUUIDCode());
        orderRepository.save(order);
        System.out.println("Sipariş oluşturuldu");

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setPriceAtPurchase(item.getPriceAtAddedTime());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
            System.out.println("Sipariş kodu: " + order.getCode());
            System.out.println("Sepet içi ürünler güncellendi");

            OrderItemHistory orderItemHistory = new OrderItemHistory();
            orderItemHistory.setProductName(item.getProduct().getName());
            orderItemHistory.setPrice(item.getPriceAtAddedTime());
            orderItemHistory.setQuantity(item.getQuantity());
            orderItemHistoryRepository.save(orderItemHistory);
            System.out.println("Sepet içerisindeki ürünler geçmişe kayıt edildi");

            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
            System.out.println("Ürünün stoğu azaltıldı");
        }

        cartItemRepository.deleteByCart(cart);
        cart.setTotal(0.0);
        cartRepository.save(cart);
        System.out.println("Sepet temizlendi");

        return convertToOrderResponseDTO(order);
    }

    public OrderResponseDTO getOrderForCode(String code) {
        Order order = orderRepository.findByCode(code);
        if (order == null) {
            throw new RuntimeException(code + " kodlu sipariş bulunamadı");
        }
        return convertToOrderResponseDTO(order);
    }
}