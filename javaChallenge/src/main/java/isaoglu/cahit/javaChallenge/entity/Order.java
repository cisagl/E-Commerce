package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends Base{

    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
    private Double total;
    @Column(unique = true)
    private String code;
}
