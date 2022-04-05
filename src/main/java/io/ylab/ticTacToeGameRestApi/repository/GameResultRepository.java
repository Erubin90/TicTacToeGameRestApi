package io.ylab.ticTacToeGameRestApi.repository;

import io.ylab.ticTacToeGameRestApi.model.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {
}
