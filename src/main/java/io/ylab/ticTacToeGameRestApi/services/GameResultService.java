package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.GameResult;
import io.ylab.ticTacToeGameRestApi.entities.Player;

public interface GameResultService {

    GameResult save(GameResult gameResult);

    GameResult create(Player player);
}
