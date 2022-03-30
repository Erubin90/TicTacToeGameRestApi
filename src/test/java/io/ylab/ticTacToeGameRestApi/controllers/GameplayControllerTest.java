package io.ylab.ticTacToeGameRestApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ylab.ticTacToeGameRestApi.TicTacToeGameRestApiApplication;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.json.GameJson;
import io.ylab.ticTacToeGameRestApi.objects.json.GameplayJson;
import io.ylab.ticTacToeGameRestApi.objects.json.PlayerJson;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameplayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private GameplayService gameplayService;

    @Test
    void createGameplay() throws JsonProcessingException {
        String name = "Ivan";
        PlayerJson playerJson = new PlayerJson(0L, name);

        ResponseEntity<String> response =
                restTemplate.postForEntity("http://localhost:" + port + "/players", playerJson, String.class);

        var body = response.getBody();
        Response<PlayerJson> playerJsonResponse = mapper.readValue(body, new TypeReference<>() {});

        playerJson = playerJsonResponse.getMessage();
        GameJson gameJson = new GameJson();
        gameJson.setId(1L);
        gameJson.setBordSize(5);
        gameJson.setAmountSymbolLine(3);
        gameJson.setTypeGame(1);

        GameplayJson gameplayJson = new GameplayJson();
        gameplayJson.setId(1L);
        gameplayJson.setGame(gameJson);
        gameplayJson.setPlayerId(playerJson.getId());
        gameplayJson.setSymbol("X");

        response = restTemplate.postForEntity("http://localhost:" + port + "/gameplay", gameplayJson, String.class);
        body = response.getBody();
        Response<GameplayJson> gameplayJsonResponse = mapper.readValue(body, new TypeReference<>() {});

        assertEquals(gameJson.toString(), gameplayJsonResponse.getMessage().getGame().toString());
    }

    @Test
    void addPlayer() throws JsonProcessingException {
        createGameplay();

        String name = "Petr";
        PlayerJson playerJson = new PlayerJson(0L, name);
        ResponseEntity<String> response =
                restTemplate.postForEntity("http://localhost:" + port + "/players", playerJson, String.class);

        var body = response.getBody();
        Response<PlayerJson> playerJsonResponse = mapper.readValue(body, new TypeReference<>() {});
        playerJson = playerJsonResponse.getMessage();

        GameplayJson gameplayJson = new GameplayJson();
        gameplayJson.setId(1L);
        gameplayJson.setPlayerId(playerJson.getId());
        gameplayJson.setSymbol("O");

        restTemplate.put("http://localhost:" + port + "/gameplay", gameplayJson, String.class);

        response = restTemplate.getForEntity("http://localhost:" + port + "/gameplay/1", String.class);
        body = response.getBody();
        Response<GameplayJson> gameplayJsonResponse = mapper.readValue(body, new TypeReference<>() {});
        var newGameplayJson = gameplayJsonResponse.getMessage();

        assertEquals(newGameplayJson.getPlayers().size(), 2);
    }

    @Test
    @Transactional
    void getGameplay() {

    }

    @Test
    void getGameplayPlayer() {
    }

    @Test
    void replayGameplayById() {
    }

}