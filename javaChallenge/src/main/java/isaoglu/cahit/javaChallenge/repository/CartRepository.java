package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.Cart;
import isaoglu.cahit.javaChallenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomer(Customer customer);
}
