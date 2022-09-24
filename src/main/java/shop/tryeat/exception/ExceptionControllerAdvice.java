package shop.tryeat.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import shop.tryeat.domain.item.exception.ItemNotFoundException;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(ItemNotFoundException exception, Model model) {
        log.error(exception.getMessage());
        log.error(String.valueOf(exception.getClass()));

        model.addAttribute("exception", exception);
        return "error/404";
    }

}
