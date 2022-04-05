package io.ylab.ticTacToeGameRestApi.utils;

import io.ylab.ticTacToeGameRestApi.dto.StepDto;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;

public class Check {

    public static void isNull(Object o, String nameParam) {
        if (o == null)
            throw new InvalidValueException(nameParam + " is null");
    }

    public static void isNullOrEmpty(String string, String nameParam) {
        isNull(string, nameParam);
        if (string.isEmpty())
            throw new InvalidValueException(nameParam + " is empty");
    }

    public static void stepDtoIsNull(StepDto stepDto) {
        isNull(stepDto.getGameplayId(), "gameplayId");
        isNull(stepDto.getGameId(), "gameId");
        isNull(stepDto.getPlayerId(), "gameId");
        isNull(stepDto.getColumn(), "column");
        isNull(stepDto.getRow(), "row");
    }
}
