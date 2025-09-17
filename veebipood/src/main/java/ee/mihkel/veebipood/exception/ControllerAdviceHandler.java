package ee.mihkel.veebipood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setTimestamp(new Date());
        errorMessage.setStatusCode(400);
        return ResponseEntity.status(400).body(errorMessage);
    }
}



// Mul on geneeriline (üldine) veateade:
// PROBLEEM: ei anta front-endile infot, mis täpselt juhtus
//    {
//        "timestamp": "2025-08-06T07:07:42.000+00:00",
//        "status": 500,
//        "error": "Internal Server Error",
//        "path": "/products"
//    }

// LAHENDUS:
// ControllerAdvice
// ErrorMessage mudel
// {
//      "message": "...."
//      "timestamp": "2025-08-06T07:07:42.000+00:00",
//      "statusCode": 400
// }