package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.model.Player;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.repository.PlayerRepository;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerServiceImp implements PlayerService {

    private final PlayerRepository repository;

    @Override
    public Player get(Long id) {
        var optional = repository.findById(id);
        var player = optional.orElseThrow(() -> new InvalidValueException("invalid playerId"));
        return player;
    }

    @Override
    public Player save(Player player) {
        Check.isNullOrEmpty(player.getName(), "name");
        player.setId(null);
        return repository.save(player);
    }

    @Override
    public Player update(Player player) {
        Check.isNull(player.getId(), "player id");
        Check.isNullOrEmpty(player.getName(), "name");
        long maxId = repository.countAllBy();
        if (maxId < player.getId())
            throw new InvalidValueException("There is no player with this id");
        return repository.save(player);
    }
}
