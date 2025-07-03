package xyz.grauberger.application.masterdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompetitionNotFoundException extends RuntimeException {
    public CompetitionNotFoundException(String message) {
        super(message);
    }

    public CompetitionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompetitionNotFoundException(long id) {
        super("Competition not found. Competition-ID: " + id);
    }
}
