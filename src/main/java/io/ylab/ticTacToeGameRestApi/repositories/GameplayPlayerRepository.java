package io.ylab.ticTacToeGameRestApi.repositories;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.entities.GameplayPlayer;
import io.ylab.ticTacToeGameRestApi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameplayPlayerRepository extends JpaRepository<GameplayPlayer, Long> {

    GameplayPlayer findByGameplayIsAndPlayerIs(Gameplay gameplay, Player player);

    @Query("FROM GameplayPlayer WHERE player.id = :playerId")
    List<GameplayPlayer> findAllByPlayerId(Long playerId);
}
