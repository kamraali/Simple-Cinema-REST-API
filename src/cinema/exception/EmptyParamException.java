package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmptyParamException extends BusinessLogicException{
    public EmptyParamException() {
        super("Parameter should not be empty!", HttpStatus.BAD_REQUEST);
    }
}