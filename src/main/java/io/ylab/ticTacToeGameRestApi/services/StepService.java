package io.ylab.ticTacToeGameRestApi.services;

import io.ylab.ticTacToeGameRestApi.dto.Board;
import io.ylab.ticTacToeGameRestApi.dto.StepDto;

public interface StepService {

    Board addStep(StepDto request);
}
