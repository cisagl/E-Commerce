package isaoglu.cahit.javaChallenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer extends Base{

    @NotNull(message = "Müşteri adı boş bırakılamaz")
    private String name;
    private String phone;
    @Email
    @NotNull(message = "Email adresi boş bırakılamaz")
    private String email;
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private Cart cart;
}
