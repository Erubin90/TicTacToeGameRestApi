package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.model.GameResult;
import io.ylab.ticTacToeGameRestApi.model.Player;
import io.ylab.ticTacToeGameRestApi.repository.GameResultRepository;
import io.ylab.ticTacToeGameRestApi.services.GameResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameResultServiceImp implements GameResultService {

    private final GameResultRepository repository;

    @Override
    public GameResult save(GameResult gameResult) {
        var saveGameResult = repository.save(gameResult);
        return saveGameResult;
    }

    @Override
    public GameResult create(Player player) {
        var gameResult = new GameResult();
        gameResult.setWinPlayer(player);
        var saveGameResult = save(gameResult);
        return saveGameResult;
    }
}
