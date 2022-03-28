package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.entities.Step;
import io.ylab.ticTacToeGameRestApi.repositories.GameplayRepository;
import io.ylab.ticTacToeGameRestApi.repositories.PlayerRepository;
import io.ylab.ticTacToeGameRestApi.repositories.StepRepository;
import io.ylab.ticTacToeGameRestApi.services.StepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StepServiceImp implements StepService {

    private final StepRepository stepRepository;
    private final GameplayRepository gameplayRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Step addStep(Step stepRequest) {

        return null;
    }
}
