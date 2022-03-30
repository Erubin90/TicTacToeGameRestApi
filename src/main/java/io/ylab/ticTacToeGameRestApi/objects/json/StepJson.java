package io.ylab.ticTacToeGameRestApi.objects.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"stepId", "playerId", "gameplayId", "gameId", "column", "row", "num", "symbol"})
public class StepJson {

    @JsonProperty("stepId")
    private Long id;

    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("gameplayId")
    private Long gameplayId;

    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("column")
    private Integer column;

    @JsonProperty("row")
    private Integer row;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("symbol")
    private String symbol;

    public StepJson(Step step) {
        this.id = step.getId();
        this.num = step.getNum();
        this.symbol = step.getSymbol();
        this.column = step.getColumn();
        this.row = step.getRow();
        this.playerId = step.getPlayer().getId();
        this.gameId = step.getGame().getId();
    }
}
