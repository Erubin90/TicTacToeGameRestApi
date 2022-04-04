package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.model.Game;
import io.ylab.ticTacToeGameRestApi.model.GameStatus;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.repositories.GameStatusRepository;
import io.ylab.ticTacToeGameRestApi.services.GameStatusService;
import io.ylab.ticTacToeGameRestApi.utils.enums.GameStatuses;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameStatusServiceImp implements GameStatusService {

    private final GameStatusRepository repository;

    @Override
    public GameStatus getLastGameStatus(Game game) {
        var optional = repository.findTopByGameOrderByIdDesc(game);
        var gameStatus = optional.orElseThrow(
                () -> new InvalidValueException("gameStatus with the game_id does not exist"));
        return gameStatus;
    }

    @Override
    public void createGameStatus(Game game, GameStatuses status) {
        var gameStatus = new GameStatus(game, status.getStatus());
        repository.save(gameStatus);
    }
}
