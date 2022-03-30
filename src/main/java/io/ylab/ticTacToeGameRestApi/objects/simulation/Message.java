package io.ylab.ticTacToeGameRestApi.objects.simulation;

import io.ylab.ticTacToeGameRestApi.objects.json.PlayerJson;
import io.ylab.ticTacToeGameRestApi.objects.json.StepJson;

import java.util.List;

public class Message {

    private final static String dash = "-> ";

    public static void printStartGame(List<PlayerJson> playerList) {
        var player1 = playerList.get(0);
        var player2 = playerList.get(1);
        System.out.println("Игра началась");
        System.out.println(player1.getName() + " - " + player1.getSymbol());
        System.out.println(player2.getName() + " - " + player2.getSymbol());
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                char simbol = aChar;
                if (simbol == 0)
                    simbol = '-';
                System.out.print("| " + simbol + ' ');
            }
            System.out.println('|');
        }
    }

    public static void printBotMove(String name, StepJson step) {
        System.out.println(name + dash + (step.getRow() + 1) + (step.getColumn() + 1));
    }

    public static void printWinPlayer(String name) {
        System.out.println("Выиграл игрок " + name);
    }

    public static void printDrawPlayers() {
        System.out.println("Ничья");
    }

    public static void printSeparator(String pattern, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(pattern);
        }
        System.out.println();
    }
}
