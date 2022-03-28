package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Player;

public interface PlayerService {

    Player getPlayer(Long id);

    Player savePlayer(Player player);
}
