package io.ylab.ticTacToeGameRestApi.objects;

import io.ylab.ticTacToeGameRestApi.entities.Step;
import io.ylab.ticTacToeGameRestApi.objects.enums.StepResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void checkForVictory() {
        int boardSize = 5;
        int amountSymbolLine = 3;

        var board1 = new Board(boardSize, amountSymbolLine);
        List<Step> steps1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            var step = new Step();
            step.setRow(i);
            step.setColumn(i);
            step.setSymbol("x");
            steps1.add(step);
        }
        board1.addAllStep(steps1);
        var stepResult = board1.getStepResult();
        assertEquals(StepResult.WIN, stepResult);

        var board2 = new Board(boardSize, amountSymbolLine);
        List<Step> steps2 = new ArrayList<>();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                var step = new Step();
                step.setRow(row);
                step.setColumn(col);
                String symbol = (row * 5 + col + 1) % 2 == 0 ? "x" : "o";
                step.setSymbol(symbol);
                steps2.add(step);
            }
        }
        board2.addAllStep(steps2);
        stepResult = board2.getStepResult();
        assertEquals(StepResult.DRAW, stepResult);

        var board3 = new Board(boardSize, amountSymbolLine);
        List<Step> steps3 = new ArrayList<>();
        var step1 = new Step();
        step1.setRow(1);
        step1.setColumn(0);
        step1.setSymbol("X");
        steps3.add(step1);
        var step2 = new Step();
        step2.setRow(2);
        step2.setColumn(0);
        step2.setSymbol("O");
        steps3.add(step2);
        var step3 = new Step();
        step3.setRow(2);
        step3.setColumn(1);
        step3.setSymbol("X");
        steps3.add(step3);
        var step4 = new Step();
        step4.setRow(3);
        step4.setColumn(3);
        step4.setSymbol("O");
        steps3.add(step4);
        var step5 = new Step();
        step5.setRow(1);
        step5.setColumn(1);
        step5.setSymbol("X");
        steps3.add(step5);
        var step6 = new Step();
        step6.setRow(1);
        step6.setColumn(3);
        step6.setSymbol("O");
        steps3.add(step6);

        board3.addAllStep(steps3);
        stepResult = board3.getStepResult();
        assertEquals(StepResult.NEXT_MOVE, stepResult);
    }
}