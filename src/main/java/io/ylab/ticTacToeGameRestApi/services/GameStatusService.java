package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.entities.GameStatus;
import io.ylab.ticTacToeGameRestApi.utils.enums.GameStatuses;

public interface GameStatusService {

    GameStatus getLastGameStatus(Game game);

    void createGameStatus(Game game, GameStatuses status);
}
