package io.ylab.ticTacToeGameRestApi.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameStatuses {
    START_GAME("start game"),
    BEING_PLAYED("being played"),
    GAME_OVER("game over");

    private final String status;
}
