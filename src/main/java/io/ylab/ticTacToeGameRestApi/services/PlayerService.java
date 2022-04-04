package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.model.Player;

public interface PlayerService {

    Player getPlayer(Long id);

    Player savePlayer(Player player);
}
