package shop.tryeat.repository.payment;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.payment.entity.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByNumber(Long number);

}
