package shop.tryeat.repository.order;

import java.util.List;
import shop.tryeat.domain.order.dto.OrderDetailSearchDto;
import shop.tryeat.domain.order.entity.Order;

public interface OrderDetailCustom {

    List<OrderDetailSearchDto> searchOrderDetails(Order order);

}
