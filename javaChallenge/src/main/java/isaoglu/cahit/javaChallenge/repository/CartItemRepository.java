package isaoglu.cahit.javaChallenge.repository;

import isaoglu.cahit.javaChallenge.entity.Cart;
import isaoglu.cahit.javaChallenge.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCart_IdAndProduct_Id(Long cartId, Long productId);
    void deleteByCart(Cart cart);

}
