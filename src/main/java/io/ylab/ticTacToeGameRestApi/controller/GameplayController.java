package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import io.ylab.ticTacToeGameRestApi.utils.Convector;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GameplayController {

    private final GameplayService service;

    @GetMapping("/gameplay/{id}")
    public Response<GameplayDto> getGameplay(@PathVariable Long id) {
        var gameplay = service.getGameplay(id);
        var gameplayJson = new GameplayDto(gameplay);
        return new Response<>(gameplayJson);
    }

    @GetMapping("/gameplay")
    public Response<List<GameplayDto>> getGameplayPlayer(@RequestParam(name = "player_id") Long playerId) {
        var gameplayList = service.getAllGameplayByPlayerId(playerId);
        var gameplayJsonList = gameplayList.stream()
                .map(GameplayDto::new)
                .collect(Collectors.toList());
        return new Response<>(gameplayJsonList);
    }

    @GetMapping("/gameplay/{id}/replay")
    public Response<String> replayGameplayById(@PathVariable Long id) {
        var gameplay = service.getGameplay(id);
        service.replay(gameplay);
        return new Response<>("The game has been successfully played");
    }

    @PostMapping("/gameplay")
    public Response<GameplayDto> createGameplay(@RequestBody GameplayDto gameplay) {
        var createGameplay = service.createGameplay(gameplay);
        gameplay.setGameplay(createGameplay);
        return new Response<>(gameplay);
    }

    @PutMapping("/gameplay")
    public Response<GameplayDto> addPlayer(@RequestBody GameplayDto gameplay) {
        var newGameplay = service.addPlayer(gameplay);
        gameplay.setGameplay(newGameplay);
        return new Response<>(gameplay);
    }

    @PostMapping("/gameplay/replay")
    public Response<String> replayGameplayByFile(@RequestBody MultipartFile multipartFile) {
        var gameplayDto = Convector.MultipartFileToGameplayDto(multipartFile);
        service.replay(gameplayDto);
        return new Response<>("The game has been successfully played");
    }
}
