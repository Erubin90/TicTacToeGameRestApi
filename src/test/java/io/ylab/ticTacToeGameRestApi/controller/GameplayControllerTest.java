package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.TicTacToeGameRestApiApplication;
import io.ylab.ticTacToeGameRestApi.dto.GameDto;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidExecutionException;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TicTacToeGameRestApiApplication.class})
@ActiveProfiles("test")
class GameplayControllerTest {

    @Autowired
    private GameplayController gameplayController;

    @Autowired
    private PlayerController playerController;

    @Test
    @Transactional
    void getGameplay_Correct() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Magomed")).getMessage();

        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());
        gameplayDto.setSymbol("x");

        var createGameplay = gameplayController.createGameplay(gameplayDto).getMessage();
        var getGameplay = gameplayController.getGameplay(createGameplay.getId()).getMessage();

        assertEquals(getGameplay.getId(), createGameplay.getId());

        assertNotNull(getGameplay.getGame());
        assertEquals(getGameplay.getGame().getId(), createGameplay.getGame().getId());
        assertEquals(getGameplay.getGame().getBordSize(), createGameplay.getGame().getBordSize());
        assertEquals(getGameplay.getGame().getAmountSymbolLine(), createGameplay.getGame().getAmountSymbolLine());
        assertEquals(getGameplay.getGame().getTypeGame(), createGameplay.getGame().getTypeGame());

        assertNotNull(getGameplay.getGameResult());
        assertEquals(getGameplay.getGameResult().getId(), createGameplay.getGameResult().getId());
    }

    @Test
    @Transactional
    void getGameplay_NoCorrectGameplayId() {
        var exception = assertThrows(InvalidValueException.class,
                () -> gameplayController.getGameplay(1421342342342L));
        assertEquals(exception.getMessage(), "invalid id");
    }

    @Test
    @Transactional
    void createGameplay_CustomGame_Correct() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();

        var gameDto = new GameDto();
        gameDto.setBordSize(5);
        gameDto.setAmountSymbolLine(3);
        gameDto.setTypeGame(2);

        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());
        gameplayDto.setSymbol("x");
        gameplayDto.setGame(gameDto);

        var response = gameplayController.createGameplay(gameplayDto);
        var createGameplay = response.getMessage();

        assertTrue(createGameplay.getId() > 0);
        assertNotNull(createGameplay.getGame());
        assertTrue(createGameplay.getGame().getId() > 0);
        assertEquals(createGameplay.getGame().getBordSize(), gameDto.getBordSize());
        assertEquals(createGameplay.getGame().getAmountSymbolLine(), gameDto.getAmountSymbolLine());
        assertEquals(createGameplay.getGame().getTypeGame(), gameDto.getTypeGame());
        assertNotNull(createGameplay.getGameResult());
        assertTrue(createGameplay.getGameResult().getId() > 0);
    }

    @Test
    @Transactional
    void createGameplay_DefaultGame_Correct() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();

        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());
        gameplayDto.setSymbol("x");

        var response = gameplayController.createGameplay(gameplayDto);
        var createGameplay = response.getMessage();

        assertTrue(createGameplay.getId() > 0);
        assertNotNull(createGameplay.getGame());
        assertTrue(createGameplay.getGame().getId() > 0);
        assertEquals(createGameplay.getGame().getBordSize(), 3);
        assertEquals(createGameplay.getGame().getAmountSymbolLine(), 3);
        assertEquals(createGameplay.getGame().getTypeGame(), 1);
        assertNotNull(createGameplay.getGameResult());
        assertTrue(createGameplay.getGameResult().getId() > 0);
    }

    @Test
    @Transactional
    void createGameplay_SymbolIsNullOrEmpty() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();
        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());

        var exception = assertThrows(InvalidValueException.class,
                () -> gameplayController.createGameplay(gameplayDto));
        assertEquals(exception.getMessage(), "symbol is null");

        gameplayDto.setSymbol("");
        exception = assertThrows(InvalidValueException.class,
                () -> gameplayController.createGameplay(gameplayDto));
        assertEquals(exception.getMessage(), "symbol is empty");
    }

    @Test
    @Transactional
    void createGameplay_DefaultGame_NoCorrectPlayerId() {
        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(112121L);
        gameplayDto.setSymbol("x");

        var exception = assertThrows(InvalidValueException.class,
                () -> gameplayController.createGameplay(gameplayDto));
        assertEquals(exception.getMessage(), "invalid playerId");
    }

    @Test
    @Transactional
    void createGameplay_GameplayIsNull() {
        var exception = assertThrows(InvalidValueException.class,
                () -> gameplayController.createGameplay(null));
        assertEquals(exception.getMessage(), "gameplay is null");
    }

    @Test
    @Transactional
    void addPlayer_Correct() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();

        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());
        gameplayDto.setSymbol("x");

        var response = gameplayController.createGameplay(gameplayDto);
        var createGameplay = response.getMessage();

        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();

        createGameplay.setPlayerId(twoPlayer.getId());
        createGameplay.setSymbol("d");
        var gameplay = gameplayController.addPlayer(createGameplay).getMessage();


        assertEquals(gameplay.getId(), createGameplay.getId());

        assertNotNull(gameplay.getPlayers());
        assertEquals(gameplay.getPlayers().size(), 2);
        assertEquals(gameplay.getPlayers().get(0).getId(), createPlayer.getId());
        assertEquals(gameplay.getPlayers().get(1).getId(), twoPlayer.getId());

        assertNotNull(gameplay.getGame());
        assertEquals(gameplay.getGame().getId(), createGameplay.getGame().getId());
        assertEquals(gameplay.getGame().getBordSize(), createGameplay.getGame().getBordSize());
        assertEquals(gameplay.getGame().getAmountSymbolLine(), createGameplay.getGame().getAmountSymbolLine());
        assertEquals(gameplay.getGame().getTypeGame(), createGameplay.getGame().getTypeGame());

        assertNotNull(gameplay.getGameResult());
        assertTrue(gameplay.getGameResult().getId() > 0);
    }

    @Test
    @Transactional
    void addPlayer_AddTreePlayer() {
        var createPlayer = playerController.createPlayer(new PlayerDto("Petr")).getMessage();

        var gameplayDto = new GameplayDto();
        gameplayDto.setPlayerId(createPlayer.getId());
        gameplayDto.setSymbol("x");

        var response = gameplayController.createGameplay(gameplayDto);
        var createGameplay = response.getMessage();

        var twoPlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();

        createGameplay.setPlayerId(twoPlayer.getId());
        createGameplay.setSymbol("d");
        var gameplay = gameplayController.addPlayer(createGameplay).getMessage();

        var treePlayer = playerController.createPlayer(new PlayerDto("Sergey")).getMessage();

        gameplay.setPlayerId(treePlayer.getId());
        gameplay.setSymbol("L");

        var exception = assertThrows(InvalidExecutionException.class,
                () -> gameplayController.addPlayer(gameplay));
        assertEquals(exception.getMessage(), "the game is full");
    }

    @Test
    void replayGameplayByFile() {
    }
}