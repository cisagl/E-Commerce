package isaoglu.cahit.javaChallenge.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {
    private String code;
    private Double total;
    private Long customerId;
    private List<OrderItemDTO> items;

    @Data
    public static class OrderItemDTO {
        private String productName;
        private Double priceAtPurchase;
        private Integer quantity;
    }
}
