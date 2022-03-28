package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.repositories.GameResultRepository;
import io.ylab.ticTacToeGameRestApi.services.GameResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameResultServiceImp implements GameResultService {

    private final GameResultRepository repository;
}
