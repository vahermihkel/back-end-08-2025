package ee.mihkel.rendipood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(NotEnoughBonusPointsException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Not enough bonus points");
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(FilmAlreadyRentedException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Film already rented");
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(TooMuchBonusDaysException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Too much bonus days");
        errorMessage.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
    }
}
