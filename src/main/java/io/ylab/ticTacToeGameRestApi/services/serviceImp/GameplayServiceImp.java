package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.exceptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.model.GameResult;
import io.ylab.ticTacToeGameRestApi.model.GameplayPlayer;
import io.ylab.ticTacToeGameRestApi.repository.GameplayPlayerRepository;
import io.ylab.ticTacToeGameRestApi.services.*;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import io.ylab.ticTacToeGameRestApi.model.Game;
import io.ylab.ticTacToeGameRestApi.model.Gameplay;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.repository.GameplayRepository;
import io.ylab.ticTacToeGameRestApi.utils.Message;
import io.ylab.ticTacToeGameRestApi.utils.enums.GameStatuses;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameplayServiceImp implements GameplayService {

    private final GameplayRepository gameplayRepository;
    private final GameplayPlayerRepository gameplayPlayerRepository;

    private final PlayerService playerService;
    private final GameService gameService;
    private final GameResultService gameResultService;
    private final GameStatusService gameStatusService;

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
    public Gameplay createGameplay(GameplayDto request) {
        var playerId = request.getPlayerId();
        var symbol = request.getSymbol();
        var gameRequest = request.getGame();

        Check.isNull(playerId, "playerId");
        Check.isNullOrEmpty(symbol, "symbol");

        int bordSize = 3;
        int amountSymbolLine = 3;
        int typeGame = 1;

        if (gameRequest != null){
            if (gameRequest.getBordSize() != null)
                bordSize = gameRequest.getBordSize();
            if (gameRequest.getAmountSymbolLine() != null) {
                if (gameRequest.getAmountSymbolLine() <= bordSize)
                    amountSymbolLine = gameRequest.getAmountSymbolLine();
                else
                    throw new DontMachValueException("amountSymbolLine = " + gameRequest.getAmountSymbolLine() + " should not be larger than the bordSize = " + bordSize);
            }
            if (gameRequest.getTypeGame() != null)
                typeGame = gameRequest.getTypeGame();
        }

        var player = playerService.get(playerId);
        var game = new Game(bordSize, amountSymbolLine, typeGame);
        var gameplayPlayer = new GameplayPlayer();
        gameplayPlayer.setPlayer(player);
        gameplayPlayer.setSymbol(symbol);
        gameplayPlayer.setNum(1);

        var createGame = gameService.save(game);
        var createGameResult = gameResultService.create(null);
        var gameplay = new Gameplay(createGame, createGameResult);
        var createGameplay = save(gameplay);

        gameplayPlayer.setGameplay(createGameplay);
        gameplayPlayerRepository.save(gameplayPlayer);

        gameStatusService.createGameStatus(createGame, GameStatuses.START_GAME);
        return createGameplay;
    }

    @Override
    public Gameplay save(Gameplay gameplay) {
        return gameplayRepository.save(gameplay);
    }

    @Override
    public Gameplay addPlayer(GameplayDto request) {
        var playerId = request.getPlayerId();
        var gameplayId = request.getId();
        var requestSymbol = request.getSymbol();
        Check.isNull(playerId, "playerId");
        Check.isNull(gameplayId, "gameplayId");
        Check.isNullOrEmpty(requestSymbol, "symbol");

        var gameplay = getGameplay(gameplayId);
        var gameplayPlayerList = gameplay.getGameplayPlayerList();
        if (gameplayPlayerList.size() > 1)
            throw new InvalidExecutionException("the game is full");

        var firstPlayerSymbol = gameplayPlayerList.get(0).getSymbol();

        if (requestSymbol.equals(firstPlayerSymbol))
            throw new InvalidExecutionException("the symbol matches another player");

        var newPlayer = playerService.get(playerId);
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

    @Override
    public void replay(Gameplay gameplay) {
        var gameplayDto = new GameplayDto(gameplay);
        replay(gameplayDto);
    }

    @Override
    public void replay(GameplayDto gameplay) {
        var players = gameplay.getPlayers();
        var steps = gameplay.getGame().getSteps();
        int boardSize = gameplay.getGame().getBordSize();
        var winPlayer = gameplay.getGameResult().getWinPlayerId();
        var matrix = new char[boardSize][boardSize];
        int countPattern = 30;

        Message.printSeparator("-", countPattern);
        Message.printStartGame(players);
        Message.printSeparator("-", countPattern);
        for (var step: steps) {
            var player = getPlayer(players, step.getPlayerId());
            Message.printBotMove(player.getName(), step);
            int row = step.getRow();
            int col = step.getColumn();
            char sing = step.getSymbol().charAt(0);
            matrix[row][col] = sing;
            Message.printMatrix(matrix);
            Message.printSeparator("-", countPattern);
        }

        if (winPlayer != null) {
            String winPlayerName = getPlayer(players, winPlayer).getName();
            Message.printWinPlayer(winPlayerName);
        }
        else
            Message.printDrawPlayers();
    }

    private PlayerDto getPlayer(List<PlayerDto> players, long playerId) {
        var firstPlayer = players.get(0);
        var lastPlayer = players.get(1);
        return firstPlayer.getId() == playerId ? firstPlayer : lastPlayer;
    }
}
