package io.ylab.ticTacToeGameRestApi.controller;

import io.ylab.ticTacToeGameRestApi.dto.Board;
import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.dto.StepDto;
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
    public Response<Board> addStep(@RequestBody StepDto request) {
        var board = service.addStep(request);
        return new Response<>(board);
    }
}
