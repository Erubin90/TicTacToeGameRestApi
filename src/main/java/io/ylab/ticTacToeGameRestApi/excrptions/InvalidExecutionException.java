package io.ylab.ticTacToeGameRestApi.excrptions;

public class InvalidExecutionException extends RuntimeException{

    public InvalidExecutionException(String message) {
        super(message);
    }
}
