package isaoglu.cahit.javaChallenge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotNull(message = "Müşteri adı boş bırakılamaz")
    private String name;
    private String phone;
    @Email
    @NotNull(message = "Email adresi boş bırakılamaz")
    private String email;
}