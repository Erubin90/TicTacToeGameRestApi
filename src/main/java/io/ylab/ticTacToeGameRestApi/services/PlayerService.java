package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.model.Player;

public interface PlayerService {

    Player get(Long id);

    Player save(Player player);

    Player update(Player player);
}
