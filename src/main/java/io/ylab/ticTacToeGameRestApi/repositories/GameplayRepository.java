package io.ylab.ticTacToeGameRestApi.repositories;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayRepository extends JpaRepository<Gameplay, Long> {
}
