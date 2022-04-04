package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.model.Game;
import io.ylab.ticTacToeGameRestApi.repository.GameRepository;
import io.ylab.ticTacToeGameRestApi.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService {

    private final GameRepository repository;


    @Override
    public Game save(Game game) {
        return repository.save(game);
    }

    @Override
    public Game create(int bordSize, int amountSymbolLine, int typeGame) {
        var game = new Game(bordSize, amountSymbolLine, typeGame);
        return save(game);
    }
}
