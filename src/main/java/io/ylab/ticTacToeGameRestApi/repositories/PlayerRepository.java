package io.ylab.ticTacToeGameRestApi.repositories;

import io.ylab.ticTacToeGameRestApi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}