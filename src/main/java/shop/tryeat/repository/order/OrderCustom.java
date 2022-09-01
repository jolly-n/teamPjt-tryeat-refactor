package shop.tryeat.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.tryeat.domain.member.entity.Member;
import shop.tryeat.domain.order.dto.OrderSearchDto;

public interface OrderCustom {

    Page<OrderSearchDto> searchOrders(Member member, Pageable pageable);

}
