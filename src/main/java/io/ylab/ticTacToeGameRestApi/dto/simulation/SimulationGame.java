package io.ylab.ticTacToeGameRestApi.dto.simulation;

import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;

import java.util.List;

public class SimulationGame {

    public static void play(GameplayDto gameplay) {
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

    private static PlayerDto getPlayer(List<PlayerDto> players, long playerId) {
        var firstPlayer = players.get(0);
        var lastPlayer = players.get(1);
        return firstPlayer.getId() == playerId ? firstPlayer : lastPlayer;
    }
}
