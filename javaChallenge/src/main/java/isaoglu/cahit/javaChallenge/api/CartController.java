package isaoglu.cahit.javaChallenge.api;

import isaoglu.cahit.javaChallenge.dto.request.CartUpdateDTO;
import isaoglu.cahit.javaChallenge.dto.response.CartResponseDTO;
import isaoglu.cahit.javaChallenge.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    private String home() {
        return "Sepet";
    }

    @GetMapping("/{id}")
    private CartResponseDTO getCart(@PathVariable("id") Long customerId) {
        return cartService.findByCustomerId(customerId);
    }

    @PostMapping("/update")
    private CartResponseDTO updateCart(@RequestBody CartUpdateDTO cartUpdateDTO) {
        return cartService.updateCart(cartUpdateDTO);
    }

    @DeleteMapping("/clear")
    private CartResponseDTO emptyCart(@RequestParam Long cartId) {
        return cartService.emptyCart(cartId);
    }

    @PostMapping("/remove")
    private CartResponseDTO removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        return cartService.removeProductFromCart(cartId, productId);
    }
}
