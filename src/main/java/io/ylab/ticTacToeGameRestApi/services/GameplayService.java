package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.objects.requests.GameplayRequest;

import java.util.List;

public interface GameplayService {

    Gameplay getGameplay(Long id);

    List<Gameplay> getAllGameplayByPlayerId(Long playerId);

    Gameplay createGameplay(GameplayRequest request);

    Gameplay addPlayer(GameplayRequest request);

}
