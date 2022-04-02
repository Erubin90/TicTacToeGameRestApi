package io.ylab.ticTacToeGameRestApi.exceptions;

public class InvalidValueException extends RuntimeException{

    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
