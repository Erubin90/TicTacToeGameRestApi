package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Game;

public interface GameService {

    Game save(Game game);

    Game create(int bordSize, int amountSymbolLine, int typeGame);
}