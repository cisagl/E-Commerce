package isaoglu.cahit.javaChallenge.api;

import isaoglu.cahit.javaChallenge.dto.request.CustomerRequestDTO;
import isaoglu.cahit.javaChallenge.dto.response.CustomerResponseDTO;
import isaoglu.cahit.javaChallenge.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponseDTO> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.addCustomer(customerRequestDTO);
    }
}
