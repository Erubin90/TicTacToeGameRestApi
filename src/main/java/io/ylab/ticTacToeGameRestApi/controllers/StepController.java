package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.objects.Board;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.objects.json.StepJson;
import io.ylab.ticTacToeGameRestApi.services.StepService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StepController {

    private final StepService service;

    @PostMapping("/gameplay/games/steps")
    public Response<Board> addStep(@RequestBody StepJson request) {
        var response = new Response<Board>();
        var board = service.addStep(request);
        response.setMessage(board);
        return response;
    }
}
