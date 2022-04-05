package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.model.Game;
import io.ylab.ticTacToeGameRestApi.model.GameStatus;
import io.ylab.ticTacToeGameRestApi.utils.enums.GameStatuses;

public interface GameStatusService {

    GameStatus getLastGameStatus(Game game);

    void createGameStatus(Game game, GameStatuses status);
}
