package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(value = "/players/{id}")
    public Response<PlayerDto> getPlayer(@PathVariable Long id) {
        var player = playerService.getPlayer(id);
        var playerJson = new PlayerDto(player);
        return new Response<>(playerJson);
    }

    @PostMapping(value = "/players")
    public Response<PlayerDto> createPlayer(@RequestBody PlayerDto player) {
        var createPlayer = playerService.savePlayer(player.getPlayer());
        player.setPlayer(createPlayer);
        return new Response<>(player);
    }

    @PutMapping(value = "/players")
    public Response<PlayerDto> updatePlayer(@RequestBody PlayerDto player) {
        var updatePlayer = playerService.savePlayer(player.getPlayer());
        player.setPlayer(updatePlayer);
        return new Response<>(player);
    }
}
