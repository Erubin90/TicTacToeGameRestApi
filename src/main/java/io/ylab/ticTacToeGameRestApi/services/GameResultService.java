package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.model.GameResult;
import io.ylab.ticTacToeGameRestApi.model.Player;

public interface GameResultService {

    GameResult save(GameResult gameResult);

    GameResult create(Player player);
}
