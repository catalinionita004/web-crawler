package com.example.webcrawler.exceptions;

import com.example.webcrawler.utils.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ErrorType.GLOBAL_EXCEPTION, List.of(new Error("", "", exception.getMessage(), "")));
        logger.error("errorMessage = {}, stackTrace = {}", exception.getMessage(), exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ErrorType.NO_SUCH_ELEMENT_EXCEPTION, List.of(new Error("", "", exception.getMessage(), "")));
        logger.error("errorMessage = {}, stackTrace = {}", exception.getMessage(), exception.getStackTrace());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ErrorType.RUNTIME_EXCEPTION, List.of(new Error("", "", exception.getMessage(), "")));
        logger.error("errorMessage = {}, stackTrace = {}", exception.getMessage(), exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotSavedException.class)
    public ResponseEntity<?> handleNotSavedExceptionException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ErrorType.NOT_SAVED_EXCEPTION, List.of(new Error("", "", exception.getMessage(), "")));
        logger.error("errorMessage = {}, stackTrace = {}", exception.getMessage(), exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResults.class)
    public ResponseEntity<?> handleNoResults(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ErrorType.NO_RESULT, List.of(new Error("", "", exception.getMessage(), "")));
        logger.error("errorMessage = {}, stackTrace = {}", exception.getMessage(), exception.getStackTrace());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getErrorType(), exception.getErrorList());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}