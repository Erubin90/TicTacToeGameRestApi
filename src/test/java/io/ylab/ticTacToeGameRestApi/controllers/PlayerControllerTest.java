package io.ylab.ticTacToeGameRestApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.json.PlayerJson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void createPlayer() throws JsonProcessingException {
        String name = "Ivan";
        PlayerJson json = new PlayerJson(0L, name);

        ResponseEntity<String> response =
                restTemplate.postForEntity("http://localhost:" + port + "/players", json, String.class);

        var body = response.getBody();
        Response<PlayerJson> playerJsonResponse = mapper.readValue(body, new TypeReference<>() {});

        assertEquals(name, playerJsonResponse.getMessage().getName());
    }

    @Test
    void getPlayer() throws JsonProcessingException {
        String name = "Petr";
        PlayerJson json1 = new PlayerJson(0L, name);
        ResponseEntity<String> response1 =
                restTemplate.postForEntity("http://localhost:" + port + "/players", json1, String.class);
        var body = response1.getBody();
        long id = mapper.readValue(body, new TypeReference<Response<PlayerJson>>() {}).getMessage().getId();

        ResponseEntity<String> response2 =
                restTemplate.getForEntity("http://localhost:" + port + "/players/" + id, String.class);

        body = response2.getBody();
        Response<PlayerJson> playerJsonResponse = mapper.readValue(body, new TypeReference<>() {});

        assertEquals(id, playerJsonResponse.getMessage().getId());
    }

    @Test
    void updatePlayer() throws JsonProcessingException {
        String name = "Igor";
        PlayerJson json1 = new PlayerJson(0L, name);
        ResponseEntity<String> response1 =
                restTemplate.postForEntity("http://localhost:" + port + "/players", json1, String.class);

        var body = response1.getBody();
        long id = mapper.readValue(body, new TypeReference<Response<PlayerJson>>() {}).getMessage().getId();

        json1.setName("Bagdan");
        restTemplate.put("http://localhost:" + port + "/players", json1);


        ResponseEntity<String> response2 =
                restTemplate.getForEntity("http://localhost:" + port + "/players/" + id, String.class);

        body = response2.getBody();
        Response<PlayerJson> playerJsonResponse = mapper.readValue(body, new TypeReference<>() {});

        assertEquals(id, playerJsonResponse.getMessage().getId());

    }
}