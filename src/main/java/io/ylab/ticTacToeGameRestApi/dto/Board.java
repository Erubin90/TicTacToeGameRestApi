package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.utils.enums.StepResult;
import io.ylab.ticTacToeGameRestApi.model.Step;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"matrix", "stepResult", "winPlayer"})
public class Board {

    @JsonProperty("matrix")
    private char[][] matrix;

    @JsonIgnore
    private int amountSymbolLine;

    @Getter
    @JsonProperty("stepResult")
    private StepResult stepResult;

    @Getter
    @JsonProperty("winPlayer")
    private PlayerDto winPlayer;

    public Board(int boardSize, int amountSymbolLine) {
        this.matrix = new char[boardSize][boardSize];
        this.amountSymbolLine = amountSymbolLine;
    }

    public void printMatrix() {
        for (var array : matrix) {
            for (char c : array) {
                System.out.print("|" + c);
            }
            System.out.println("|");
        }
    }

    public StepResult addStep(Step step) {
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
            winPlayer = new PlayerDto(step.getPlayer());
        return stepResult;
    }

    public StepResult addAllStep(List<Step> steps) {
        for (var step : steps) {
            stepResult = addStep(step);
            if (stepResult == StepResult.WIN)
                break;
        }
        return stepResult;
    }

    public StepResult checkForVictory(Step step) {
        int boardSize = matrix.length;
        char playerSymbol = step.getSymbol().charAt(0);
        var resultGame = StepResult.NEXT_MOVE;

        int numberEmptySeats = boardSize * boardSize;
        //???????????????? ???? ?????????????????? ?? ??????????????????????
        for (int i = 0; i < boardSize; i++) {
            int horizon = amountSymbolLine;
            int vertical = amountSymbolLine;
            for (int j = 0; j < boardSize; j++) {
                //???????????????? ???????????? ???? ??????????????????????
                char horizonSymbol = matrix[i][j];
                if (horizonSymbol == playerSymbol)
                    horizon--;
                else
                    horizon = amountSymbolLine;
                //???????????????? ???????????? ???? ??????????????????
                char verticalSymbol = matrix[j][i];
                if (verticalSymbol == playerSymbol)
                    vertical--;
                else
                    vertical = amountSymbolLine;
                if (horizon == 0 || vertical == 0) {
                    resultGame = StepResult.WIN;
                    return resultGame;
                }
                //???????????????? ???? ??????????
                if (horizonSymbol != 0)
                    numberEmptySeats--;
                if (numberEmptySeats == 0) {
                    resultGame = StepResult.DRAW;
                    return resultGame;
                }
            }
        }

        //???????????????? ???????????? ???? ????????????????????
        int leftDiagonal = amountSymbolLine;
        int rightDiagonal = amountSymbolLine;
        //???????? ???????????????????? ???????????????????? ???????????????? ?????????? ?????????????? ??????????
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
        //???????? ???????????????????? ???????????????????? ???????????????? ???????????? ?????????????? ??????????
        else {
            int countDiagonal = boardSize % 2 == 0 ? boardSize - 1 : boardSize;
            int middleDiagonal = countDiagonal / 2 + 1;
            for (int length = 1; length <= countDiagonal; length++) {
                boolean flag = length <= middleDiagonal;
                int depth = flag ? length : middleDiagonal * 2 - length;
                int i = flag ? 0 : length - middleDiagonal;
                int j = flag ? middleDiagonal - length : 0;
                for (int x = 0; x < depth; x++) {
                    for (int num = 0; num < amountSymbolLine; num++) {
                        int row = i + num;
                        int col = j + num;
                        if (row == boardSize || col == boardSize)
                            break;
                        int[] diagonalsPoint = checkDiagonals(boardSize, row, col, playerSymbol, leftDiagonal, rightDiagonal);
                        leftDiagonal = diagonalsPoint[0];
                        rightDiagonal = diagonalsPoint[1];
                        if (leftDiagonal == 0 || rightDiagonal == 0) {
                            resultGame = StepResult.WIN;
                            return resultGame;
                        }
                    }
                    i++;
                    j++;
                }
            }

        }
        return resultGame;
    }

    private int[] checkDiagonals(int boardSize, int row, int col, char playerSymbol, int leftDiagonal, int rightDiagonal) {
        //???????????????? ???? ?????????? ??????????????????
        System.out.println(row + ", " + col);
        char leftDiagonalSymbol = matrix[row][col];
        if (leftDiagonalSymbol == playerSymbol)
            leftDiagonal--;
        else
            leftDiagonal = amountSymbolLine;
        //???????????????? ???? ???????????? ??????????????????
        int rightCol = boardSize - 1 - col;
        char rightDiagonalSymbol = matrix[row][rightCol];
        if (rightDiagonalSymbol == playerSymbol)
            rightDiagonal--;
        else
            rightDiagonal = amountSymbolLine;
        return new int[] {leftDiagonal, rightDiagonal};
    }
}
