package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);
}
