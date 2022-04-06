package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.ylab.ticTacToeGameRestApi.model.GameResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({"Player", "id", "winPlayer"})
public class GameResultDto {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonProperty("player")
    @JacksonXmlProperty(localName = "Player")
    private PlayerDto winPlayer;

    @JsonIgnore
    public GameResultDto(GameResult gameResult) {
        if (gameResult != null) {
            this.id = gameResult.getId();
            var winPlayer = gameResult.getWinPlayer();
            if (winPlayer != null)
                this.winPlayer = new PlayerDto(winPlayer);
        }
    }
}
