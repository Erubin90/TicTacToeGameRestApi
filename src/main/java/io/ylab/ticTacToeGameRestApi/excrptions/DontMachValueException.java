package io.ylab.ticTacToeGameRestApi.excrptions;

public class DontMachValueException extends RuntimeException{

    public DontMachValueException(String message) {
        super(message);
    }
}
