package io.ylab.ticTacToeGameRestApi.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.entities.Step;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.enums.StepResult;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"matrix", "stepResult", "winPlayer"})
public class Board {

    @JsonProperty("matrix")
    private char[][] matrix;

    @JsonIgnore
    private final int amountSymbolLine;

    @Getter
    @JsonProperty("stepResult")
    private StepResult stepResult;

    @Getter
    @JsonProperty("winPlayer")
    private Player winPlayer;

    public Board(int boardSize, int amountSymbolLine) {
        this.matrix = new char[boardSize][boardSize];
        this.amountSymbolLine = amountSymbolLine;
    }

    public void addStep(Step step) {
        int row = step.getRow();
        int col = step.getColumn();
        char symbol = step.getSymbol().charAt(0);
        char matrixSymbol = matrix[row][col];
        if (matrixSymbol == 0)
            matrix[row][col] = symbol;
        else
            throw new InvalidValueException("column or row invalid value");
        stepResult = checkForVictory(step);
        if (stepResult == StepResult.WIN)
            winPlayer = step.getPlayer();
    }

    public void addAllStep(List<Step> steps) {
        for (var step : steps) {
            addStep(step);
        }
    }

    private StepResult checkForVictory(Step step) {
        int boardSize = matrix.length;
        char playerSymbol = step.getSymbol().charAt(0);
        var resultGame = StepResult.NEXT_MOVE;

        int numberEmptySeats = boardSize * boardSize;

        //Проверка по вертикали и горизонтали
        int horizon = amountSymbolLine;
        int vertical = amountSymbolLine;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                //проверка победы по горизонтали
                char horizonSymbol = matrix[i][j];
                if (horizonSymbol == playerSymbol)
                    horizon--;
                else
                    horizon = amountSymbolLine;
                //проверка победы по вертикали
                char verticalSymbol = matrix[j][i];
                if (verticalSymbol == playerSymbol)
                    vertical--;
                else
                    vertical = amountSymbolLine;
                if (horizon == 0 || vertical == 0) {
                    resultGame = StepResult.WIN;
                    return resultGame;
                }
                //Проверка на ничью
                if (horizonSymbol != 0)
                    numberEmptySeats--;
                if (numberEmptySeats == 0) {
                    resultGame = StepResult.DRAW;
                    return resultGame;
                }
            }
        }

        //Проверка победы по вертикалям
        int leftDiagonal = amountSymbolLine;
        int rightDiagonal = amountSymbolLine;
        //Если количество выигравших символов равно размеру доски
        if (boardSize == amountSymbolLine) {
            for (int i = 0; i < boardSize; i++) {
                int[] diagonalsPoint = checkDiagonals(boardSize, i, i, playerSymbol, leftDiagonal, rightDiagonal);
                leftDiagonal = diagonalsPoint[0];
                rightDiagonal = diagonalsPoint[1];
                if (leftDiagonal == 0 || rightDiagonal == 0) {
                    resultGame = StepResult.WIN;
                    return resultGame;
                }
            }
        }
        //Если количество выигравших символов меньше размера доски
        else {
            int count = boardSize - amountSymbolLine + 1;
            for (int x = 0; x < count; x++) {
                for (int c = 0; c < count; c++) {
                    for (int i = c; i < amountSymbolLine + c; i++) {
                        int row = i + x;
                        int[] diagonalsPoint = checkDiagonals(boardSize, i, row, playerSymbol, leftDiagonal, rightDiagonal);
                        leftDiagonal = diagonalsPoint[0];
                        rightDiagonal = diagonalsPoint[1];
                        if (leftDiagonal == 0 || rightDiagonal == 0) {
                            resultGame = StepResult.WIN;
                            return resultGame;
                        }
                    }
                }
            }
        }
        return resultGame;
    }

    private int[] checkDiagonals(int boardSize, int col, int row, char playerSymbol, int leftDiagonal, int rightDiagonal) {
        //проверка по левой диагонали
        char leftDiagonalSymbol = matrix[row][col];
        if (leftDiagonalSymbol == playerSymbol)
            leftDiagonal--;
        else
            leftDiagonal = amountSymbolLine;
        //проверка по правой диагонали
        int rightCol = boardSize - 1 - col;
        char rightDiagonalSymbol = matrix[row][rightCol];
        if (rightDiagonalSymbol == playerSymbol)
            rightDiagonal--;
        else
            rightDiagonal = amountSymbolLine;
        return new int[] {leftDiagonal, rightDiagonal};
    }
}
