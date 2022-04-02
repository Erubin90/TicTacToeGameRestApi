package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.GameResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({"id", "winPlayer"})
public class GameResultDto {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonProperty("winPlayerId")
    private Long winPlayerId;

    public GameResultDto(GameResult gameResult) {
        if (gameResult != null) {
            this.id = gameResult.getId();
            var winPlayer = gameResult.getWinPlayer();
            if (winPlayer != null)
                this.winPlayerId = winPlayer.getId();
        }
    }
}
