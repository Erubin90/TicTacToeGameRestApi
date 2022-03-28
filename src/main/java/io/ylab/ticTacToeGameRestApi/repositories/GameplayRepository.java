package io.ylab.ticTacToeGameRestApi.repositories;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameplayRepository extends JpaRepository<Gameplay, Long> {

    @Query()
    List<Gameplay> findAllByPlayerId(Long playerId);
}
