package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.tools.Check;
import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.requests.GameplayRequest;
import io.ylab.ticTacToeGameRestApi.repositories.GameplayRepository;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameplayServiceImp implements GameplayService {

    private final GameplayRepository gameplayRepository;
    private final PlayerService playerService;

    @Override
    public Gameplay getGameplay(Long id) {
        var optional = gameplayRepository.findById(id);
        var gameplay = optional.orElseThrow(() -> new InvalidValueException("invalid id"));
        return gameplay;
    }

    @Override
    public List<Gameplay> getAllGameplayByPlayerId(Long playerId) {
        var gameplayList = gameplayRepository.findAllByPlayerId(playerId);
        return gameplayList;
    }

    @Override
    public Gameplay createGameplay(GameplayRequest request) {
        var playerRequest = request.getPlayer();
        Check.playerRequest(playerRequest);
        var gameRequest = request.getGame();
        Check.gameRequest(gameRequest);

        var gameplay = new Gameplay();

        var players = new ArrayList<Player>();
        var player = playerService.getPlayer(playerRequest.getId());
        players.add(player);
        gameplay.setPlayers(players);

        int bordSize = gameRequest.getBordSize();
        int amountSymbolLine = gameRequest.getAmountSymbolLine();
        int typeGame = gameRequest.getTypeGame();
        var game = new Game(bordSize, amountSymbolLine, typeGame);
        gameplay.setGame(game);

        var saveGameplay = gameplayRepository.save(gameplay);
        return saveGameplay;
    }

    @Override
    public Gameplay addPlayer(GameplayRequest request) {
        var playerRequest = request.getPlayer();
        Check.playerRequest(playerRequest);

        var gameplayId = request.getId();

        var gameplay = getGameplay(gameplayId);
        var players = gameplay.getPlayers();
        if  (players.size() == 1) {
            var player = playerService.getPlayer(playerRequest.getId());
            players.add(player);
            var saveGameplay = gameplayRepository.save(gameplay);
            return saveGameplay;
        }
        else
            throw new InvalidExecutionException("the game is full");
    }
}
