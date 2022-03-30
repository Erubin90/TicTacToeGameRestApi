package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.repositories.PlayerRepository;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerServiceImp implements PlayerService {

    private final PlayerRepository repository;

    @Override
    public Player getPlayer(Long id) {
        var optional = repository.findById(id);
        var player = optional.orElseThrow(() -> new InvalidValueException("invalid playerId"));
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        var savedPlayer = repository.save(player);
        return savedPlayer;
    }
}
