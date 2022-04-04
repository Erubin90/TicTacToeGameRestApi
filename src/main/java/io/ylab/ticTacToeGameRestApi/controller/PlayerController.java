package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.dto.PlayerDto;
import io.ylab.ticTacToeGameRestApi.services.PlayerService;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping(value = "/players/{id}")
    public Response<PlayerDto> getPlayer(@PathVariable Long id) {
        Check.isNull(id, "id");
        var player = playerService.get(id);
        var playerJson = new PlayerDto(player);
        return new Response<>(playerJson);
    }

    @PostMapping(value = "/players")
    public Response<PlayerDto> createPlayer(@RequestBody PlayerDto request) {
        Check.isNull(request, "request");
        var createPlayer = playerService.save(request.getPlayer());
        request.setPlayer(createPlayer);
        return new Response<>(request);
    }

    @PutMapping(value = "/players")
    public Response<PlayerDto> updatePlayer(@RequestBody PlayerDto request) {
        Check.isNull(request, "request");
        var updatePlayer = playerService.update(request.getPlayer());
        request.setPlayer(updatePlayer);
        return new Response<>(request);
    }
}
