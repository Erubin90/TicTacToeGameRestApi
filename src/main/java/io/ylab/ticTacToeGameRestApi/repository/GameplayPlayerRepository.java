package io.ylab.ticTacToeGameRestApi.repository;

import io.ylab.ticTacToeGameRestApi.model.GameplayPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameplayPlayerRepository extends JpaRepository<GameplayPlayer, Long> {

    @Query("FROM GameplayPlayer WHERE player.id = :playerId")
    List<GameplayPlayer> findAllByPlayerId(Long playerId);
}
