package io.ylab.ticTacToeGameRestApi.exceptions;

public class InvalidExecutionException extends RuntimeException{

    public InvalidExecutionException(String message) {
        super(message);
    }
}
