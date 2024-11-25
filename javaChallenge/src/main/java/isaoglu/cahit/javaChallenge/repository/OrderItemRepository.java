package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
