package io.ylab.ticTacToeGameRestApi.handlers;

import io.ylab.ticTacToeGameRestApi.excrptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(InvalidValueException exception) {
        Response<String> date = new Response<>(exception.getMessage(), ErrorType.INVALID_VALUE);
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(InvalidExecutionException exception) {
        Response<String> date = new Response<>(exception.getMessage(), ErrorType.INVALID_EXECUTION);
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<Response<String>> handleException(DontMachValueException exception) {
        Response<String> date = new Response<>(exception.getMessage(), ErrorType.DONT_MACH_VALUE);
        return new ResponseEntity<>(date, HttpStatus.BAD_REQUEST);
    }
}
