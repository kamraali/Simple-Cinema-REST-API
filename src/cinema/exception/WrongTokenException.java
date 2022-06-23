package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WrongTokenException extends BusinessLogicException{
    public WrongTokenException() {
        super("Wrong token!", HttpStatus.BAD_REQUEST);
    }
}
