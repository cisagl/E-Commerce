package isaoglu.cahit.javaChallenge.service;

import isaoglu.cahit.javaChallenge.dto.request.CustomerRequestDTO;
import isaoglu.cahit.javaChallenge.dto.response.CustomerResponseDTO;
import isaoglu.cahit.javaChallenge.entity.Customer;
import isaoglu.cahit.javaChallenge.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartService cartService;

    // Customer nesnesi dtoya çevrildi
    private CustomerResponseDTO convertToResponseDTO(Customer customer) {
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setId(customer.getId());
        responseDTO.setName(customer.getName());
        responseDTO.setPhone(customer.getPhone());
        responseDTO.setEmail(customer.getEmail());
        return responseDTO;
    }

    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer();
        customer.setName(customerRequestDTO.getName());
        customer.setPhone(customerRequestDTO.getPhone());
        customer.setEmail(customerRequestDTO.getEmail());
        customerRepository.save(customer);
        cartService.createCart(customer.getId());
        return convertToResponseDTO(customer);
    }
}
