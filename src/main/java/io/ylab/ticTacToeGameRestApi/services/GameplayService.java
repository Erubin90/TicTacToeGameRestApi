package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.objects.json.GameplayJson;

import java.util.List;

public interface GameplayService {

    Gameplay getGameplay(Long id);

    List<Gameplay> getAllGameplayByPlayerId(Long playerId);

    Gameplay createGameplay(GameplayJson request);

    Gameplay save(Gameplay gameplay);

    Gameplay addPlayer(GameplayJson request);

}
