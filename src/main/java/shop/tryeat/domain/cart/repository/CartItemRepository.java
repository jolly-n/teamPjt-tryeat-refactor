package shop.tryeat.domain.cart.repository;

import java.util.List;
import java.util.Optional;
import shop.tryeat.domain.cart.entity.Cart;
import shop.tryeat.domain.cart.entity.CartItem;
import shop.tryeat.domain.item.entity.Item;

public interface CartItemRepository {

    Long save(CartItem cartItem);

    Optional<CartItem> findById(Long cartItemId);

    Optional<CartItem> findByCartAndItem(Cart cart, Item item);

    List<CartItem> findByCart(Cart cart);

    void delete(CartItem cartItem);

}
