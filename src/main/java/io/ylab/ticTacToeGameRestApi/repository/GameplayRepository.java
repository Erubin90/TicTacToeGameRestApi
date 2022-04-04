package io.ylab.ticTacToeGameRestApi.repository;

import io.ylab.ticTacToeGameRestApi.model.Gameplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayRepository extends JpaRepository<Gameplay, Long> {
}
