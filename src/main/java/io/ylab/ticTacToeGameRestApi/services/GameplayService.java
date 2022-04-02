package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;

import java.util.List;

public interface GameplayService {

    Gameplay getGameplay(Long id);

    List<Gameplay> getAllGameplayByPlayerId(Long playerId);

    Gameplay createGameplay(GameplayDto request);

    Gameplay save(Gameplay gameplay);

    Gameplay addPlayer(GameplayDto request);

    void replay(Gameplay gameplay);

    void replay(GameplayDto gameplayDto);
}
