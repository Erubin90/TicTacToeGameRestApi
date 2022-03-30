package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.json.PlayerJson;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(value = "/players/{id}")
    public Response<PlayerJson> getPlayer(@PathVariable Long id) {
        var player = playerService.getPlayer(id);
        var playerJson = new PlayerJson(player);
        var response = new Response<>(playerJson);
        return response;
    }

    @PostMapping(value = "/players")
    public Response<PlayerJson> createPlayer(@RequestBody PlayerJson player) {
        var createPlayer = playerService.savePlayer(player.getPlayer());
        player.setPlayer(createPlayer);
        var response = new Response<>(player);
        return response;
    }

    @PutMapping(value = "/players")
    public Response<PlayerJson> updatePlayer(@RequestBody PlayerJson player) {
        var updatePlayer = playerService.savePlayer(player.getPlayer());
        player.setPlayer(updatePlayer);
        var response = new Response<>(player);
        return response;
    }
}
