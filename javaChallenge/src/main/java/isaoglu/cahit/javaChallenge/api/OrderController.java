package isaoglu.cahit.javaChallenge.api;

import isaoglu.cahit.javaChallenge.dto.request.OrderRequestDTO;
import isaoglu.cahit.javaChallenge.dto.response.OrderResponseDTO;
import isaoglu.cahit.javaChallenge.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    private String home() {
        return "Sipariş";
    }

    @GetMapping("/{code}")
    private OrderResponseDTO getOrderForCode(@PathVariable("code") String code) {
        return orderService.getOrderForCode(code);
    }

    @PostMapping("/place")
    private OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.placeOrder(orderRequestDTO);
    }
}
