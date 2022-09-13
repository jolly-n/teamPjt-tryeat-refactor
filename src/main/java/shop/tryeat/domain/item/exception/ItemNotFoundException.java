package shop.tryeat.domain.item.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import shop.tryeat.exception.BusinessException;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException(String message) {
        super(message, NOT_FOUND);
    }

}
