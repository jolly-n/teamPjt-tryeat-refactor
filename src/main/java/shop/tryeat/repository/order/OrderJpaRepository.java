package shop.tryeat.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.order.entity.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderCustom {

}
