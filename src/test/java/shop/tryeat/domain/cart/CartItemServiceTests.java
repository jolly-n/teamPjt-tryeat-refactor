package shop.tryeat.domain.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.tryeat.domain.cart.entity.Cart;
import shop.tryeat.domain.cart.entity.CartItem;
import shop.tryeat.domain.cart.repository.CartRepository;
import shop.tryeat.domain.cart.service.CartItemService;
import shop.tryeat.domain.item.entity.Item;
import shop.tryeat.domain.item.repository.ItemRepository;
import shop.tryeat.domain.member.entity.Member;
import shop.tryeat.domain.member.repository.MemberRepository;
import shop.tryeat.repository.cart.CartItemJpaRepository;

@Transactional
@SpringBootTest
class CartItemServiceTests {
    @Autowired
    CartItemJpaRepository cartItemJpaRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemService sut;

    private Item saveItem() {
        Item item = Item.builder().build();
        itemRepository.save(item);
        return item;
    }

    private Member saveMember() {
        Member member = Member.builder().email("test@test.com").build();
        memberRepository.save(member);
        return member;
    }

    private Cart saveCart() {
        Member member = saveMember();
        Cart cart = Cart.from(member);
        cartRepository.save(cart);
        return cart;
    }

    @Test
    void 장바구니에_상품이_없다면_상품_추가() {
        // given
        Item item = saveItem();

        CartItem cartItem = CartItem.builder().item(item).build();

        // when
        Long savedCartItemId = sut.addCartItem(cartItem); // 장바구니 상품 아이디

        // then
        assertNotNull(cartItemJpaRepository.findById(savedCartItemId));
    }

    @Test
    void 장바구니에_상품이_있다면_개수_증가() {
        // given
        Item item = saveItem();

        CartItem cartItem = CartItem.builder().item(item).quantity(2).build();
        cartItemJpaRepository.save(cartItem);

        // when
        Long savedCartItemId = sut.addCartItem(cartItem);

        // then
        assertThat(cartItemJpaRepository.findById(savedCartItemId).get().getQuantity()).isEqualTo(4);
    }

    @Test
    void 장바구니에_담긴_상품_조회() {
        // given
        Item itemA = saveItem();
        Item itemB = saveItem();

        Cart cart = saveCart();

        CartItem cartItemA = CartItem.builder().item(itemA).cart(cart).build();
        CartItem cartItemB = CartItem.builder().item(itemB).cart(cart).build();

        cartItemJpaRepository.save(cartItemA);
        cartItemJpaRepository.save(cartItemB);

        // when
        List<CartItem> cartItemList = sut.findCartItemList(cart);

        // then
        assertThat(cartItemList).hasSize(2);
    }

    @Test
    void 장바구니에_담긴_상품_수량_변경() {
        // given
        Item item = saveItem();

        CartItem cartItem = CartItem.builder().item(item).quantity(2).build();
        cartItemJpaRepository.save(cartItem);

        // when
        sut.updateCartItemQuantity(cartItem.getId(), 5);

        // then
        assertThat(cartItem.getQuantity()).isEqualTo(5);
    }

    @Test
    void 장바구니에_담긴_상품_삭제() {
        // given
        Item item = saveItem();

        Cart cart = saveCart();

        CartItem cartItem = CartItem.builder().item(item).cart(cart).build();
        cartItemJpaRepository.save(cartItem);

        // when
        sut.deleteCartItem(cartItem.getId());

        // then
        assertThat(cartItemJpaRepository.findByCart(cart)).isEmpty();
    }

}