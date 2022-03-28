package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(value = "/players/{id}")
    public Response<Player> getPlayer(@PathVariable Long id) {
        var response = new Response<Player>();
        var player = playerService.getPlayer(id);
        response.setMessage(player);
        return response;
    }

    @PostMapping(value = "/players")
    public Response<Player> createPlayer(@RequestBody Player player) {
        var response = new Response<Player>();
        var createPlayer = playerService.savePlayer(player);
        response.setMessage(createPlayer);
        return response;
    }

    @PutMapping(value = "/players")
    public Response<Player> updatePlayer(@RequestBody Player player) {
        var response = new Response<Player>();
        var id = playerService.savePlayer(player);
        response.setMessage(id);
        return response;
    }
}
