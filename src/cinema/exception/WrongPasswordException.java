package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WrongPasswordException extends BusinessLogicException{
    public WrongPasswordException() {
        super("The password is wrong!", HttpStatus.UNAUTHORIZED);
    }
}