package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.TicTacToeGameRestApiApplication;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TicTacToeGameRestApiApplication.class})
@ActiveProfiles("test")
class PlayerControllerTest {

    @Autowired
    private PlayerController playerController;

    @Test
    void getPlayerCorrect() {
        var player = new PlayerDto("Invan");
        var response = playerController.createPlayer(player);
        var createPlayer = response.getMessage();

        response = playerController.getPlayer(createPlayer.getId());
        var getPlayer = response.getMessage();

        assertEquals(createPlayer.getId(), getPlayer.getId());
        assertEquals(createPlayer.getName(), getPlayer.getName());
    }

    @Test
    void getPlayerNoCorrectId() {
        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.getPlayer(123123142342L));
        assertEquals(exception.getMessage(), "invalid playerId");
    }

    @Test
    void getPlayerIdIsNull() {
        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.getPlayer(null));
        assertEquals(exception.getMessage(), "id is null");
    }

    @Test
    void createPlayerCorrect() {
        var player = new PlayerDto("Invan");
        var response = playerController.createPlayer(player);
        var createPlayer = response.getMessage();

        assertTrue(createPlayer.getId() > 0 &&
                createPlayer.getName().equals(player.getName()));
    }

    @Test
    void createPlayerNameIsNullOrEmpty() {
        var player = new PlayerDto();

        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.createPlayer(player));
        assertEquals(exception.getMessage(), "name is null");

        player.setName("");

        exception = assertThrows(InvalidValueException.class,
                () -> playerController.createPlayer(player));
        assertEquals(exception.getMessage(), "name is empty");
    }

    @Test
    void createPlayerPlayerIsNull() {
        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.createPlayer(null));
        assertEquals(exception.getMessage(), "request is null");
    }

    @Test
    void updatePlayerCorrect() {
        var player = new PlayerDto("Invan");
        var response = playerController.createPlayer(player);
        var createPlayer = response.getMessage();
        createPlayer.setName("Petr");

        response = playerController.updatePlayer(createPlayer);
        var updatePlayer = response.getMessage();

        assertEquals(updatePlayer.getId(), createPlayer.getId());
        assertEquals(updatePlayer.getName(), createPlayer.getName());
    }

    @Test
    void updatePlayerNoCorrectId() {
        var player = new PlayerDto("Invan");
        player.setId(123121423L);

        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.updatePlayer(player));
        assertEquals(exception.getMessage(), "There is no player with this id");
    }

    @Test
    void updatePlayerPlayerIsNull() {
        var exception = assertThrows(InvalidValueException.class,
                () -> playerController.updatePlayer(null));
        assertEquals(exception.getMessage(), "request is null");
    }

}