package shop.tryeat.repository.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.tryeat.domain.qna.dto.QuestionSearchCondition;
import shop.tryeat.domain.qna.dto.QuestionSearchDto;

public interface QuestionCustom {

    Page<QuestionSearchDto> searchQuestion(QuestionSearchCondition condition, Pageable pageable);

}
