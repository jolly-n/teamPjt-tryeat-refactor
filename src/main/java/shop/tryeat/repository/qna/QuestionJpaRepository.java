package shop.tryeat.repository.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.qna.entity.Question;

public interface QuestionJpaRepository extends JpaRepository<Question, Long>, QuestionCustom {

}
