package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.entities.GameStatus;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.repositories.GameStatusRepository;
import io.ylab.ticTacToeGameRestApi.services.GameStatusService;
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
    public void save(GameStatus gameStatus) {
        var game = gameStatus.getGame();
        var newStatus = gameStatus.getStatus();
        var lastStatus = getLastGameStatus(game).getStatus();
        if (!newStatus.equals(lastStatus)) {
            repository.save(gameStatus);
        }
    }
}
