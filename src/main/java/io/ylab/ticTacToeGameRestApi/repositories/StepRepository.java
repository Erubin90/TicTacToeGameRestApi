package io.ylab.ticTacToeGameRestApi.repositories;

import io.ylab.ticTacToeGameRestApi.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
}
