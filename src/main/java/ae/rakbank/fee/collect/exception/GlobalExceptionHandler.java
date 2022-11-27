package ae.rakbank.fee.collect.exception;

import ae.rakbank.fee.collect.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

import static ae.rakbank.fee.collect.utility.AppConstants.*;
import static ae.rakbank.fee.collect.utility.LogMessages.RESPONSE_SENT;


/**
 * @author imran
 * Global Exception Handler Class to handle application exceptions
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler({ FeeDetailNotFoundException.class, StudentNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception exception) {
        log.info(RESPONSE_SENT, HttpStatus.NOT_FOUND.name());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusDesc(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }


    /**
     * @param exception
     * @return
     */
    @ExceptionHandler({ ApiInvokerServiceException.class })
    public ResponseEntity<ErrorResponse> handleApiInvokerServiceException(Exception exception) {
        HttpStatus httpStatus = null;
        String message = null;
        log.info(RESPONSE_SENT, HttpStatus.NOT_FOUND.name());
        if(exception.getMessage().contains(CONNECT_EXCEPTION)) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            message = STUDENT_SERVICE_UNAVAILABLE;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = STUDENT_SERVICE_ERROR;
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .statusDesc(httpStatus.name())
                .message(message)
                .build();
        return new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
    }


    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ FeeCollectionServiceException.class, HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<ErrorResponse> handleFeeCollectionServiceException(Exception exception) {
        log.info(RESPONSE_SENT, HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .statusDesc(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception exception) {
        log.info(RESPONSE_SENT, HttpStatus.BAD_REQUEST.name());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusDesc(HttpStatus.BAD_REQUEST.name())
                .build();
        if(exception instanceof MethodArgumentNotValidException) {
            Map<String, String> errors = new HashMap<>();
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            errorResponse.setMessage(errors.toString());
        }
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
