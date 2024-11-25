package isaoglu.cahit.javaChallenge.service;

import isaoglu.cahit.javaChallenge.dto.request.CartUpdateDTO;
import isaoglu.cahit.javaChallenge.dto.response.CartResponseDTO;
import isaoglu.cahit.javaChallenge.entity.Cart;
import isaoglu.cahit.javaChallenge.entity.CartItem;
import isaoglu.cahit.javaChallenge.entity.Customer;
import isaoglu.cahit.javaChallenge.entity.Product;
import isaoglu.cahit.javaChallenge.repository.CartItemRepository;
import isaoglu.cahit.javaChallenge.repository.CartRepository;
import isaoglu.cahit.javaChallenge.repository.CustomerRepository;
import isaoglu.cahit.javaChallenge.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    // Cart nesnesi dtoya çevrildi
    private CartResponseDTO convertToResponseDTO(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(cart.getId());
        cartResponseDTO.setTotal(cart.getTotal());

        // CartItem nesnesi dtoya çevrildi
        List<CartResponseDTO.CartItemResponseDTO> itemDTOs = cart.getItems().stream()
                .map(item -> {
                    CartResponseDTO.CartItemResponseDTO itemDTO = new CartResponseDTO.CartItemResponseDTO();
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setProductName(item.getProduct().getName());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setPriceAtAddedTime(item.getPriceAtAddedTime());
                    return itemDTO;
                })
                .collect(Collectors.toList());

        cartResponseDTO.setItems(itemDTOs);

        return cartResponseDTO;
    }

    public CartResponseDTO createCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);
        cartRepository.save(cart);
        System.out.println("Müşteri ile beraber sepet de oluşturuldu");
        return convertToResponseDTO(cart);
    }

    public CartResponseDTO findByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Cart cart = cartRepository.findByCustomer(customer);
        return convertToResponseDTO(cart);
    }

    public CartResponseDTO updateCart(CartUpdateDTO cartUpdateDTO) {
        Cart cart = cartRepository.findById(cartUpdateDTO.getCartId()).orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));
        Product product = productRepository.findById(cartUpdateDTO.getProductId()).orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
        CartItem cartItem = cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), product.getId());

        if (product.getStock() < cartUpdateDTO.getQuantity()) {
            throw new RuntimeException("Yetersiz stok");
        }

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + cartUpdateDTO.getQuantity());
            cartItem.setPriceAtAddedTime(product.getPrice());
            cartItemRepository.save(cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartUpdateDTO.getQuantity());
            cartItem.setPriceAtAddedTime(product.getPrice());
            cartItemRepository.save(cartItem);
        }

        cart.setTotal(0.0);
        for (CartItem item : cart.getItems()) {
            cart.setTotal(cart.getTotal() + item.getQuantity() * item.getPriceAtAddedTime());
        }
        cartRepository.save(cart);
        System.out.println("Sepet güncellendi");

        return convertToResponseDTO(cart);
    }

    public CartResponseDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem cartItem = cartItemRepository.findByCart_IdAndProduct_Id(cart.getId(), product.getId());

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            System.out.println("Ürün sepetten silindi");
            cart.setTotal(0.0);
            for (CartItem item : cart.getItems()) {
                cart.setTotal(cart.getTotal() + item.getQuantity() * item.getPriceAtAddedTime());
            }
        }

        cartRepository.save(cart);
        System.out.println("Sepet güncellendi");

        return convertToResponseDTO(cart);
    }

    @Transactional
    public CartResponseDTO emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();

        if (cart.getTotal() == 0) {
            throw new RuntimeException("Sepet zaten boş");
        } else {
            cartItemRepository.deleteByCart(cart);
            System.out.println("Sepet boşaltıldı");
            cart.setTotal(0.0);
        }

        cartRepository.save(cart);
        System.out.println("Sepet güncellendi");

        return convertToResponseDTO(cart);
    }
}
