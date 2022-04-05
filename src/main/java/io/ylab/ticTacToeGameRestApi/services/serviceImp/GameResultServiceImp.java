package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.model.GameResult;
import io.ylab.ticTacToeGameRestApi.model.Player;
import io.ylab.ticTacToeGameRestApi.repository.GameResultRepository;
import io.ylab.ticTacToeGameRestApi.services.GameResultService;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameResultServiceImp implements GameResultService {

    private final GameResultRepository repository;

    @Override
    public GameResult save(GameResult gameResult) {
        Check.isNull(gameResult, "gameResult");
        return repository.save(gameResult);
    }

    @Override
    public GameResult create(Player player) {
        var gameResult = new GameResult();
        gameResult.setWinPlayer(player);
        return save(gameResult);
    }
}
