package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import io.ylab.ticTacToeGameRestApi.entities.Player;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.requests.GameplayRequest;
import io.ylab.ticTacToeGameRestApi.services.GameplayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("/cabinets")
@AllArgsConstructor
public class GameplayController {

    private final GameplayService service;

    @GetMapping("/gameplay/{id}")
    public Response<Gameplay> getGameplay(@PathVariable Long id) {
        var response = new Response<Gameplay>();
        var gameplay = service.getGameplay(id);
        response.setMessage(gameplay);
        return response;
    }

    @GetMapping("/gameplay/{id}/replay")
    public void replayGameplayById(@PathVariable Long id) {

    }

    @PostMapping("/gameplay")
    public Response<Gameplay> createGameplay(@RequestBody GameplayRequest request) {
        var response = new Response<Gameplay>();
        var createGameplay = service.createGameplay(request);
        response.setMessage(createGameplay);
        return response;
    }

    @PostMapping("/gameplay/replay")
    public void replayGameplayByFile(@RequestBody MultipartFile multipartFile) {

    }
}
