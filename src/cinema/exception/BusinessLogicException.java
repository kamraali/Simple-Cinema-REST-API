package cinema.exception;

import org.springframework.http.HttpStatus;

public class BusinessLogicException extends RuntimeException{
    HttpStatus errorStatus;
    public BusinessLogicException (String message, HttpStatus status){
        super(message);
        this.errorStatus = status;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }
}