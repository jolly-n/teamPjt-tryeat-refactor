package shop.tryeat.domain.payment.repository;

import java.util.Optional;
import shop.tryeat.domain.payment.entity.Payment;

public interface PaymentRepository {

    Long save(Payment payment);

    Optional<Payment> findByNum(Long Number);

}
