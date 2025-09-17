package ee.mihkel.veebipood.exception;

import lombok.Data;

import java.util.Date;

// @Datat kasutatakse ainult andmemudelite jaoks, mitte andmebaasitabelite jaoks
@Data // ---> tagataustal teeb valmis @Getter, @Setter, @NoArgsConstructor
public class ErrorMessage {
    private String message;
    private Date timestamp;
    private int statusCode;
}


// LAHENDUS:
// ControllerAdvice
// ErrorMessage mudel
// {
//      "message": "...."
//      "timestamp": "2025-08-06T07:07:42.000+00:00",
//      "statusCode": 400
// }