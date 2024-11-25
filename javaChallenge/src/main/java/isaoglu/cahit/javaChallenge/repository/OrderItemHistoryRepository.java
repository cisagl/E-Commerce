package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.OrderItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemHistoryRepository extends JpaRepository<OrderItemHistory, Long> {

}
