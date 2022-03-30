package io.ylab.ticTacToeGameRestApi.tools;

import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;

public class Check {

    public static void isNull(Object o, String nameParam) {
        if (o == null)
            throw new InvalidValueException(nameParam + " is null");
    }

    public static void isNullOrEmpty(String string, String nameParam) {
        isNull(string, nameParam);
        if (string.isEmpty())
            throw new InvalidValueException(nameParam + "is empty");
    }
}
