package org.wscict.bank.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Custom API Exception
    @ExceptionHandler(ApiException.class)
    @ResponseStatus
    public ErrorResponse handleApiException(ApiException ex, HttpServletRequest request) {
        log.error("API Exception: {}", ex.getMessage());

        HttpStatus status = ex instanceof ResourceNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;

        return new ErrorResponse(status.value(),
                status.getReasonPhrase(),
                ex.getErrorCode(),
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    // Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        log.warn("Validation Failed: {}", message);

        return new ErrorResponse(

                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "BANK_VALIDATION",
                message,
                request.getRequestURI()
        );
    }

    //Fallback (Unexpected Errors)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex, HttpServletRequest request) {

        log.error("Unexpected Error", ex);

        return new ErrorResponse(
                500,
                "Internal Server Error.",
                "BANK_500",
                "Something went wrong. Please contact support.",
                request.getRequestURI()
        );
    }
}