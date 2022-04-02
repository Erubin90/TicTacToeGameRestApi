package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.entities.GameStatus;

public interface GameStatusService {

    GameStatus getLastGameStatus(Game game);

    void save(GameStatus gameStatus);
}
