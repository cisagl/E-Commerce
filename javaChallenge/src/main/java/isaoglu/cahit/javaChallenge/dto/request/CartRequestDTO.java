package isaoglu.cahit.javaChallenge.dto.request;

import lombok.Data;

@Data
public class CartRequestDTO {

    private Long customerId;
    private Double total;
}