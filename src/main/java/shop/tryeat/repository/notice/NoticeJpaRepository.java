package shop.tryeat.repository.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.notice.entity.Notice;

public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {

}
