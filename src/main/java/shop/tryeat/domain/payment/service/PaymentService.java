package shop.tryeat.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.tryeat.domain.payment.entity.Payment;
import shop.tryeat.domain.payment.repository.PaymentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    /**
     * 결제 정보 저장
     */
    @Transactional
    public Long register(Payment payment) {
        return repository.save(payment);
    }

}
