package xyz.grauberger.application.collection.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderAlreadyCompletedException extends RuntimeException {
    public OrderAlreadyCompletedException(String message) {
        super(message);
    }

    public OrderAlreadyCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
