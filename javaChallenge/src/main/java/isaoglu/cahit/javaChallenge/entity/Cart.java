package isaoglu.cahit.javaChallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
    private Double total;
}

