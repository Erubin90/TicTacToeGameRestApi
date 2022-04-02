package io.ylab.ticTacToeGameRestApi.utils.enums;

import io.ylab.ticTacToeGameRestApi.entities.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum StepResult {
    WIN,
    DRAW,
    NEXT_MOVE;

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }
}
