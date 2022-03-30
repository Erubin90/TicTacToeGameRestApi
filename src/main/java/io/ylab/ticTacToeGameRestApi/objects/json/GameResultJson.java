package io.ylab.ticTacToeGameRestApi.objects.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.GameResult;
import io.ylab.ticTacToeGameRestApi.objects.enums.StepResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "winPlayer"})
public class GameResultJson {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonProperty("winPlayerId")
    private Long winPlayerId;

    public GameResultJson(GameResult gameResult) {
        if (gameResult != null) {
            this.id = gameResult.getId();
            var winPlayer = gameResult.getWinPlayer();
            if (winPlayer != null)
                this.winPlayerId = winPlayer.getId();
        }
    }
}
