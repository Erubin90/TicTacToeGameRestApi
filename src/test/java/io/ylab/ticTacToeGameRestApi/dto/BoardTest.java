package io.ylab.ticTacToeGameRestApi.dto;

import io.ylab.ticTacToeGameRestApi.model.Step;
import io.ylab.ticTacToeGameRestApi.utils.enums.StepResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void Draw() {
        Board board = new Board(3, 3);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 0, 2, null, null));
        steps.add(new Step(null, 4, "o", 1, 0, null, null));
        steps.add(new Step(null, 5, "x", 1, 1, null, null));
        steps.add(new Step(null, 6, "o", 1, 2, null, null));
        steps.add(new Step(null, 7, "x", 2, 1, null, null));
        steps.add(new Step(null, 8, "o", 2, 0, null, null));
        steps.add(new Step(null, 9, "x", 2, 2, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.DRAW);
    }

    @Test
    void winHorizon3x3() {
        Board board = new Board(3, 3);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 1, 1, null, null));
        steps.add(new Step(null, 3, "x", 0, 2, null, null));
        steps.add(new Step(null, 4, "o", 1, 0, null, null));
        steps.add(new Step(null, 5, "x", 0, 1, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winVertical3x3() {
        Board board = new Board(3, 3);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 1, 0, null, null));
        steps.add(new Step(null, 4, "o", 0, 2, null, null));
        steps.add(new Step(null, 5, "x", 2, 0, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winRightDiagonal3x3() {
        Board board = new Board(3, 3);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 1, 1, null, null));
        steps.add(new Step(null, 4, "o", 0, 2, null, null));
        steps.add(new Step(null, 5, "x", 2, 2, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winLeftDiagonal3x3() {
        Board board = new Board(3, 3);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 2, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 1, 1, null, null));
        steps.add(new Step(null, 4, "o", 1, 0, null, null));
        steps.add(new Step(null, 5, "x", 2, 0, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winHorizon5x4() {
        Board board = new Board(5, 4);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 1, 0, null, null));
        steps.add(new Step(null, 3, "x", 0, 1, null, null));
        steps.add(new Step(null, 4, "o", 1, 1, null, null));
        steps.add(new Step(null, 5, "x", 0, 2, null, null));
        steps.add(new Step(null, 6, "o", 1, 2, null, null));
        steps.add(new Step(null, 7, "x", 0, 3, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winVertical5x4() {
        Board board = new Board(5, 4);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 0, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 1, 0, null, null));
        steps.add(new Step(null, 4, "o", 0, 2, null, null));
        steps.add(new Step(null, 5, "x", 2, 0, null, null));
        steps.add(new Step(null, 6, "o", 0, 3, null, null));
        steps.add(new Step(null, 7, "x", 3, 0, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winRightDiagonal5x4() {
        Board board = new Board(5, 4);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 1, null, null));
        steps.add(new Step(null, 2, "o", 0, 1, null, null));
        steps.add(new Step(null, 3, "x", 1, 2, null, null));
        steps.add(new Step(null, 4, "o", 0, 2, null, null));
        steps.add(new Step(null, 5, "x", 2, 3, null, null));
        steps.add(new Step(null, 6, "o", 0, 3, null, null));
        steps.add(new Step(null, 7, "x", 3, 4, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }

    @Test
    void winLeftDiagonal5x4() {
        Board board = new Board(5, 4);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(null, 1, "x", 0, 3, null, null));
        steps.add(new Step(null, 2, "o", 4, 0, null, null));
        steps.add(new Step(null, 3, "x", 1, 2, null, null));
        steps.add(new Step(null, 4, "o", 1, 0, null, null));
        steps.add(new Step(null, 5, "x", 2, 1, null, null));
        steps.add(new Step(null, 6, "o", 2, 0, null, null));
        steps.add(new Step(null, 7, "x", 3, 0, null, null));

        board.addAllStep(steps);
        assertEquals(board.getStepResult(), StepResult.WIN);
    }
}