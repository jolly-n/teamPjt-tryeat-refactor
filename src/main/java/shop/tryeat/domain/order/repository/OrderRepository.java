package shop.tryeat.domain.order.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.tryeat.domain.member.entity.Member;
import shop.tryeat.domain.order.dto.OrderSearchDto;
import shop.tryeat.domain.order.entity.Order;

public interface OrderRepository {

    Long save(Order order);

    Optional<Order> findById(Long id);

    Page<OrderSearchDto> searchOrders(Member member, Pageable pageable);

}
