package io.ylab.ticTacToeGameRestApi.excrptions;

public class InvalidValueException extends RuntimeException{

    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
