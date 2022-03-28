package io.ylab.ticTacToeGameRestApi.tools;

import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.requests.GameRequest;
import io.ylab.ticTacToeGameRestApi.objects.requests.PlayerRequest;

public class Check {

    public static void playerRequest(PlayerRequest player) {
        if (player != null) {
            var playerId = player.getId();
            if (playerId == null)
                throw new InvalidValueException("player_id == null");
        }
        else
            throw new InvalidValueException("player is null");
    }

    public static void gameRequest(GameRequest game) {

    }


}
