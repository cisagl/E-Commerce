package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem extends Base{

    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private Integer quantity;
    @Column(name = "price_at_added_time")
    private Double priceAtAddedTime;
}
