package xyz.grauberger.application.masterdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(String message) {
        super(message);
    }

    public TournamentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TournamentNotFoundException(long id) {
        super("Tournament not found. Tournament-ID: " + id);
    }
}
