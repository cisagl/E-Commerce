package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem extends Base{

    @ManyToOne
    private Order order;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price_at_purchase")
    private Double priceAtPurchase;
    private Integer quantity;
}
