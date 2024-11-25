package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product extends Base{

    @NotNull(message = "Ürün adı boş bırakılamaz")
    private String name;
    @PositiveOrZero(message = "Stok pozitif ya da sıfır olabilir")
    private Integer stock;
    @PositiveOrZero(message = "Fiyat pozitif ya da sıfır olabilir")
    private Double price;
}
