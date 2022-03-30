package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.objects.Board;
import io.ylab.ticTacToeGameRestApi.objects.json.StepJson;

public interface StepService {

    Board addStep(StepJson request);
}
