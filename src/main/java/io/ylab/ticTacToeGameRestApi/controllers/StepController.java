package io.ylab.ticTacToeGameRestApi.controllers;

import io.ylab.ticTacToeGameRestApi.entities.Step;
import io.ylab.ticTacToeGameRestApi.objects.Response;
import io.ylab.ticTacToeGameRestApi.services.StepService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("cabinets/gameplay/games")
@AllArgsConstructor
public class StepController {

    private final StepService service;

    @PostMapping("/steps")
    public Response<?> addStep(@RequestBody Step step) {
        var response = new Response<>();
        var addStep = service.addStep(step);
        response.setMessage(addStep);
        return response;
    }
}
