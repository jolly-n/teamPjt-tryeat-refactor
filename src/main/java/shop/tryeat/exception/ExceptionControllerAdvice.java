package shop.tryeat.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception exception, WebRequest request) {
        log.error(exception.getMessage());

        ExceptionResponse exceptionResponse
                = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception, WebRequest request) {
        log.error(exception.getMessage());

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));

        return ResponseEntity.status(exception.getStatus()).body(exceptionResponse);
    }

}
