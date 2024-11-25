package isaoglu.cahit.javaChallenge.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseDTO {

    private Long id;
    private Double total;
    private Long customerId;
    private List<CartItemResponseDTO> items;

    @Data
    public static class CartItemResponseDTO {
        private Long productId;
        private String productName;
        private int quantity;
        private Double priceAtAddedTime;
    }
}