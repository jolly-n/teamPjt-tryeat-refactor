package shop.tryeat.domain.order.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.tryeat.domain.order.entity.Order;
import shop.tryeat.domain.order.entity.OrderDetail;
import shop.tryeat.domain.order.repository.OrderDetailRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public Long save(OrderDetail orderDetail) {
        orderDetail.itemRemoveStock();
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> findOrderDetailsByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }

}
