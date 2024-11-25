package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCode(String code);
    List<Order> findByCustomerId(Long customerId);
}
