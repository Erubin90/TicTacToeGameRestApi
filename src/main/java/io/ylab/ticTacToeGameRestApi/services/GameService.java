package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.model.Game;

public interface GameService {

    Game get(Long id);

    Game save(Game game);

    Game create(int bordSize, int amountSymbolLine, int typeGame);
}
