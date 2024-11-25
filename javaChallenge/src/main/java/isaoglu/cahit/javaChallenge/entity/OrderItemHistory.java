package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_item_history")
@Data
public class OrderItemHistory extends Base{
    private String productName;
    private Double price;
    private int quantity;
}
