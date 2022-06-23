package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class AlreadySoldException extends BusinessLogicException{
    public AlreadySoldException() { super("The ticket has been already purchased!", HttpStatus.BAD_REQUEST);}

}