package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OutOfBoundsException extends BusinessLogicException{
    public OutOfBoundsException() {
        super("The number of a row or a column is out of bounds!", HttpStatus.BAD_REQUEST);
    }
}