package io.ylab.ticTacToeGameRestApi.services.serviceImp;

import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.entities.Step;
import io.ylab.ticTacToeGameRestApi.exceptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.dto.Board;
import io.ylab.ticTacToeGameRestApi.services.GameStatusService;
import io.ylab.ticTacToeGameRestApi.utils.enums.StepResult;
import io.ylab.ticTacToeGameRestApi.dto.StepDto;
import io.ylab.ticTacToeGameRestApi.repositories.StepRepository;
import io.ylab.ticTacToeGameRestApi.services.GameResultService;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import io.ylab.ticTacToeGameRestApi.services.StepService;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StepServiceImp implements StepService {

    private final StepRepository stepRepository;
    private final GameplayService gameplayService;
    private final GameResultService gameResultService;
    private final GameStatusService gameStatusService;

    @Override
    public Board addStep(StepDto request) {
        var gameplayId = request.getGameplayId();
        var gameId = request.getGameId();
        var playerId = request.getPlayerId();
        var column = request.getColumn();
        var row = request.getRow();

        Check.isNull(gameplayId, "gameplayId");
        Check.isNull(gameId, "gameId");
        Check.isNull(playerId, "gameId");
        Check.isNull(column, "column");
        Check.isNull(row, "row");

        var gameplay = gameplayService.getGameplay(gameplayId);

        //Проверка gameId
        var game = gameplay.getGame();
        if (game.getId() != gameId.longValue())
            throw new DontMachValueException("the passed values [playerId, gameplayId, gameId] do not match");
        //Проверка playerId
        Player player = null;
        String playerSymbol = null;
        int playerNum = 0;
        for (var gameplayPlayer : gameplay.getGameplayPlayerList()) {
            var gamePlayer = gameplayPlayer.getPlayer();
            if (gamePlayer.getId() == playerId.longValue()) {
                player = gamePlayer;
                playerSymbol = gameplayPlayer.getSymbol();
                playerNum = gameplayPlayer.getNum();
            }
        }
        if (player == null)
            throw new DontMachValueException("the passed values [playerId, gameplayId, gameId] do not match");
        //Проверка на заполненность таблицы
        var stepList = game.getSteps();
        int moveNumber = stepList.size() + 1;
        int bordSize = game.getBordSize();
        if (bordSize * bordSize < moveNumber)
            throw new InvalidExecutionException("The board is completely filled");
        //Проверка является данный ход игрока
        int whoShouldWalk = moveNumber % 2 == 0 ? 2 : 1;
        if (whoShouldWalk != playerNum)
            throw new InvalidExecutionException("Now is your opponent's move");
        //Проверка column и row
        if (column >= bordSize || row >= bordSize || column < 0 || row < 0)
            throw new InvalidExecutionException("column or row invalid value");

        //Создаем Step
        var step = new Step();
        step.setGame(game);
        step.setPlayer(player);
        step.setSymbol(playerSymbol);
        step.setColumn(column);
        step.setRow(row);
        stepList.add(step);

        //Проверяем является ли ход выигрышным
        int amountSymbolLine = game.getAmountSymbolLine();
        var board = new Board(bordSize, amountSymbolLine);
        var stepResult = board.addAllStep(stepList);

        //Сохраняем step в бд
        stepRepository.save(step);

        //Создам GameResult
        if (stepResult == StepResult.WIN || stepResult == StepResult.DRAW) {
            gameResultService.create(board.getWinPlayer().getPlayer());
            gameplayService.save(gameplay);
        }
        return board;
    }
}
