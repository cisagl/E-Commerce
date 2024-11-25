package isaoglu.cahit.javaChallenge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartUpdateDTO {
    @NotNull(message = "Sepet id'si boş bırakılamaz")
    private Long cartId;
    @NotNull(message = "Ürün id'si boş bırakılamaz")
    private Long productId;
    @NotNull(message = "Miktar boş bırakılamaz")
    private int quantity;
}
