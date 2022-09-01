package shop.tryeat.repository.cart;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.cart.entity.Cart;
import shop.tryeat.domain.cart.entity.CartItem;
import shop.tryeat.domain.item.entity.Item;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndItem(Cart cart, Item item);

    List<CartItem> findByCart(Cart cart);

}
