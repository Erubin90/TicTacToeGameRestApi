package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.TicTacToeGameRestApiApplication;
import io.ylab.ticTacToeGameRestApi.dto.Board;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.dto.StepDto;
import io.ylab.ticTacToeGameRestApi.exceptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.model.Player;
import io.ylab.ticTacToeGameRestApi.model.Step;
import io.ylab.ticTacToeGameRestApi.utils.enums.StepResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TicTacToeGameRestApiApplication.class})
@ActiveProfiles("test")
class StepControllerTest {

    @Autowired
    private PlayerController playerController;

    @Autowired
    private GameplayController gameplayController;

    @Autowired
    private StepController stepController;

    @Test
    @Transactional
    void addStep_Correct() {
        var onePlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();
        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();
        var gameplay = createGameplay(onePlayer, twoPlayer);

        List<List<Integer>> steps = new ArrayList<>();
        steps.add(List.of(0, 0));
        steps.add(List.of(2, 2));
        steps.add(List.of(0, 1));
        steps.add(List.of(2, 1));
        steps.add(List.of(0, 2));

        var step = new StepDto();
        step.setGameId(gameplay.getGame().getId());
        step.setGameplayId(gameplay.getId());
        Board board = null;
        for (int i = 0; i < steps.size(); i++) {
            long playerId = i % 2 == 0 ? onePlayer.getId() : twoPlayer.getId();
            step.setPlayerId(playerId);
            var move = steps.get(i);
            step.setRow(move.get(0));
            step.setColumn(move.get(1));
            board = stepController.addStep(step).getMessage();
        }
        test(board);
    }

    @Test
    @Transactional
    void addStep_NoCorrectGameId() {
        var onePlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();
        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();
        var gameplay = createGameplay(onePlayer, twoPlayer);

        var step = new StepDto();
        step.setGameId(12312313131L);
        step.setGameplayId(gameplay.getId());
        step.setPlayerId(onePlayer.getId());
        step.setRow(0);
        step.setColumn(0);
        var exception = assertThrows(DontMachValueException.class,
                () -> stepController.addStep(step));
        assertEquals(exception.getMessage(), "The passed values [playerId, gameplayId, gameId] do not match");
    }

    @Test
    @Transactional
    void addStep_NoCorrectGameplayId() {
        var onePlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();
        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();
        var gameplay = createGameplay(onePlayer, twoPlayer);

        var step = new StepDto();
        step.setGameId(gameplay.getGame().getId());
        step.setGameplayId(1231231231231L);
        step.setPlayerId(onePlayer.getId());
        step.setRow(0);
        step.setColumn(0);
        var exception = assertThrows(InvalidValueException.class,
                () -> stepController.addStep(step));
        assertEquals(exception.getMessage(), "invalid id");
    }

    @Test
    @Transactional
    void addStep_NoCorrectPlayerId() {
        var onePlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();
        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();
        var gameplay = createGameplay(onePlayer, twoPlayer);

        var step = new StepDto();
        step.setGameId(gameplay.getGame().getId());
        step.setGameplayId(gameplay.getId());
        step.setPlayerId(12312313L);
        step.setRow(0);
        step.setColumn(0);
        var exception = assertThrows(DontMachValueException.class,
                () -> stepController.addStep(step));
        assertEquals(exception.getMessage(), "The passed values [playerId, gameplayId, gameId] do not match");
    }

    private void test(Board board) {
        assertNotNull(board);
        var player = board.getWinPlayer();
        if(board.getStepResult() == StepResult.WIN) {
            assertNotNull(player);
            assertNotNull(player.getId());
            assertNotNull(player.getName());
        }
        else {
            assertNull(player);
        }
    }

    private GameplayDto createGameplay(PlayerDto onePlayer, PlayerDto twoPlayer) {
        var createGameplay = new GameplayDto();
        createGameplay.setPlayerId(onePlayer.getId());
        createGameplay.setSymbol("x");
        createGameplay = gameplayController.createGameplay(createGameplay).getMessage();
        createGameplay.setPlayerId(twoPlayer.getId());
        createGameplay.setSymbol("d");
        return gameplayController.addPlayer(createGameplay).getMessage();
    }
}