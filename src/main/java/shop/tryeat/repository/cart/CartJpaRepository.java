package shop.tryeat.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.cart.entity.Cart;
import shop.tryeat.domain.member.entity.Member;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
    Cart findByMember(Member member);

}
