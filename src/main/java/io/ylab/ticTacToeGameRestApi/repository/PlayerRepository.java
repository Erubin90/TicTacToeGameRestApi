package io.ylab.ticTacToeGameRestApi.repository;

import io.ylab.ticTacToeGameRestApi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Long countAllBy();
}
