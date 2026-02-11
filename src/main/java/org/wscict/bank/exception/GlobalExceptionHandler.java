package org.wscict.bank.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    // Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return new ErrorResponse(
                400,
                "Validation Failed",
                message,
                request.getRequestURI()
        );
    }

    // Resource Not Found
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(
            RuntimeException ex,
            HttpServletRequest request
    ){
        return new ErrorResponse(
                404,
                "Resource Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}





//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(AccountNotFoundException.class)
//    public ResponseEntity<String> handleAccountNotFound(
//            AccountNotFoundException ex){
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    }
//}
