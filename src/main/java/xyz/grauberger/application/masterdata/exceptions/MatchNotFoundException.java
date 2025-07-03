package xyz.grauberger.application.masterdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(String message) {
        super(message);
    }

    public MatchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatchNotFoundException(long id) {
        super("Match not found. Match-ID: " + id);
    }
}
