package io.ylab.ticTacToeGameRestApi.handlers;

import io.ylab.ticTacToeGameRestApi.dto.ErrorMessage;
import io.ylab.ticTacToeGameRestApi.exceptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.utils.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(InvalidValueException exception) {
        var error = new ErrorMessage(exception.getMessage(), ErrorType.INVALID_VALUE);
        var date = new Response<String>(error);
        return new ResponseEntity<>(date, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(InvalidExecutionException exception) {
        var error = new ErrorMessage(exception.getMessage(), ErrorType.INVALID_EXECUTION);
        var date = new Response<String>(error);
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(DontMachValueException exception) {
        var error = new ErrorMessage(exception.getMessage(), ErrorType.DONT_MACH_VALUE);
        var date = new Response<String>(error);
        return new ResponseEntity<>(date, HttpStatus.NOT_FOUND);
    }
}
