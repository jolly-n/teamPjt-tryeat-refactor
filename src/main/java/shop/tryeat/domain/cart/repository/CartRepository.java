package shop.tryeat.domain.cart.repository;

import shop.tryeat.domain.cart.entity.Cart;
import shop.tryeat.domain.member.entity.Member;

public interface CartRepository {

    Long save(Cart cart);

    Cart findByMember(Member member);

}
