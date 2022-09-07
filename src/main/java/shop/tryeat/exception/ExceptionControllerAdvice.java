package shop.tryeat.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public final ExceptionResponse handleAllException(Exception exception, WebRequest request) {
        return new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(BusinessException.class)
    public final ExceptionResponse handleNotFoundException(Exception exception, WebRequest request) {
        return new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
    }

}
