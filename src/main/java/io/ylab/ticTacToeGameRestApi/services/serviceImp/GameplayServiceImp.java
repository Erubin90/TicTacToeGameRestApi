package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.entities.GameplayPlayer;
import io.ylab.ticTacToeGameRestApi.repositories.GameRepository;
import io.ylab.ticTacToeGameRestApi.repositories.GameplayPlayerRepository;
import io.ylab.ticTacToeGameRestApi.tools.Check;
import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.json.GameplayJson;
import io.ylab.ticTacToeGameRestApi.repositories.GameplayRepository;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameplayServiceImp implements GameplayService {

    private final GameplayRepository gameplayRepository;
    private final PlayerService playerService;
    private final GameRepository gameRepository;
    private final GameplayPlayerRepository gameplayPlayerRepository;

    @Override
    public Gameplay getGameplay(Long id) {
        var optional = gameplayRepository.findById(id);
        var gameplay = optional.orElseThrow(() -> new InvalidValueException("invalid id"));
        return gameplay;
    }

    @Override
    public List<Gameplay> getAllGameplayByPlayerId(Long playerId) {
        var gameplayPlayerList = gameplayPlayerRepository.findAllByPlayerId(playerId);
        List<Gameplay> gameplayList = new ArrayList<>();
        if (gameplayPlayerList.size() > 0) {
            gameplayList = gameplayPlayerList.stream()
                    .map(GameplayPlayer::getGameplay)
                    .collect(Collectors.toList());
        }
        return gameplayList;
    }

    @Override
    public Gameplay createGameplay(GameplayJson request) {
        var playerId = request.getPlayerId();
        var gameRequest = request.getGame();
        var symbol = request.getSymbol();
        var bordSize = gameRequest.getBordSize();
        var amountSymbolLine = gameRequest.getAmountSymbolLine();
        var typeGame = gameRequest.getTypeGame();

        Check.isNull(playerId, "playerId");
        Check.isNull(gameRequest, "game");
        Check.isNullOrEmpty(symbol, "symbol");
        Check.isNull(bordSize, "bordSize");
        Check.isNull(amountSymbolLine, "amountSymbolLine");
        Check.isNull(typeGame, "typeGame");

        var game = new Game(bordSize, amountSymbolLine, typeGame);
        var saveGame = gameRepository.save(game);

        var gameplay = new Gameplay();
        gameplay.setGame(saveGame);
        var saveGameplay = save(gameplay);

        var player = playerService.getPlayer(playerId);

        var gameplayPlayer = new GameplayPlayer();
        gameplayPlayer.setPlayer(player);
        gameplayPlayer.setGameplay(saveGameplay);
        gameplayPlayer.setSymbol(symbol);
        gameplayPlayer.setNum(1);
        gameplayPlayerRepository.save(gameplayPlayer);

        return saveGameplay;
    }

    @Override
    public Gameplay save(Gameplay gameplay) {
        var saveGameplay = gameplayRepository.save(gameplay);
        return saveGameplay;
    }

    @Override
    public Gameplay addPlayer(GameplayJson request) {
        var playerId = request.getPlayerId();
        var gameplayId = request.getId();
        var requestSymbol = request.getSymbol();
        Check.isNull(playerId, "playerId");
        Check.isNull(gameplayId, "gameplayId");
        Check.isNullOrEmpty(requestSymbol, "symbol");

        var gameplay = getGameplay(gameplayId);
        var players = gameplay.getPlayers();

        if (players.size() > 1)
            throw new InvalidExecutionException("the game is full");

        var gameplayPlayerList = gameplay.getGameplayPlayerList();
        var firstPlayerSymbol = gameplayPlayerList.get(0).getSymbol();

        if (requestSymbol.equals(firstPlayerSymbol))
            throw new InvalidExecutionException("the symbol matches another player");

        var newPlayer = playerService.getPlayer(playerId);
        var newGameplayPlayer = new GameplayPlayer();
        newGameplayPlayer.setGameplay(gameplay);
        newGameplayPlayer.setPlayer(newPlayer);
        newGameplayPlayer.setSymbol(requestSymbol);
        newGameplayPlayer.setNum(2);
        gameplayPlayerRepository.save(newGameplayPlayer);

        gameplayPlayerList.add(newGameplayPlayer);
        gameplay.setGameplayPlayerList(gameplayPlayerList);
        return gameplay;
    }
}
