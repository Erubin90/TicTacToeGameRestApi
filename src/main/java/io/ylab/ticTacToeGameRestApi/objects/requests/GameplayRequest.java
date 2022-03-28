package io.ylab.ticTacToeGameRestApi.objects.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"gameplayId", "player", "game"})
public class GameplayRequest {

    @JsonProperty("gameplayId")
    private Long id;

    @JsonProperty("player")
    private PlayerRequest player;

    @JsonProperty("game")
    private GameRequest game;

    @JsonSetter("player")
    public void setPlayer(PlayerRequest player) {
        if (player != null)
            this.player = player;
        else
            throw new InvalidValueException("player is null");
    }
}
