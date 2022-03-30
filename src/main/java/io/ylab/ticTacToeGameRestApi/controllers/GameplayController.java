package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.json.GameplayJson;
import io.ylab.ticTacToeGameRestApi.objects.simulation.SimulationGame;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class GameplayController {

    private final GameplayService service;

    @GetMapping("/gameplay/{id}")
    public Response<GameplayJson> getGameplay(@PathVariable Long id) {
        var gameplay = service.getGameplay(id);
        var gameplayJson = new GameplayJson(gameplay);
        var response = new Response<>(gameplayJson);
        return response;
    }

    @GetMapping("/gameplay")
    public Response<List<GameplayJson>> getGameplayPlayer(@RequestParam(name = "player_id") Long playerId) {
        var gameplayList = service.getAllGameplayByPlayerId(playerId);
        var gameplayJsonList = gameplayList.stream()
                .map(GameplayJson::new)
                .collect(Collectors.toList());
        var response = new Response<>(gameplayJsonList);
        return response;
    }

    @GetMapping("/gameplay/{id}/replay")
    public Response<String> replayGameplayById(@PathVariable Long id) {
        var gameplay = service.getGameplay(id);
        var gameplayJson = new GameplayJson(gameplay);
        SimulationGame.play(gameplayJson);
        var response = new Response<>("The game has been successfully played");
        return response;
    }

    @PostMapping("/gameplay")
    public Response<GameplayJson> createGameplay(@RequestBody GameplayJson gameplay) {
        var createGameplay = service.createGameplay(gameplay);
        gameplay.setGameplay(createGameplay);
        var response = new Response<>(gameplay);
        return response;
    }

    @PutMapping("/gameplay")
    public Response<GameplayJson> addPlayer(@RequestBody GameplayJson gameplay) {
        var newGameplay = service.addPlayer(gameplay);
        gameplay.setGameplay(newGameplay);
        var response = new Response<>(gameplay);
        return response;
    }

//    @PostMapping("/gameplay/replay")
//    public Response<String> replayGameplayByFile(@RequestBody MultipartFile multipartFile) {
//        var response = new Response<>("The game has been successfully played");
//        return response;
//    }
}
