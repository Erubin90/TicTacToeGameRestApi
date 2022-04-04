package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import io.ylab.ticTacToeGameRestApi.model.Step;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "Step")
@JsonPropertyOrder({"stepId", "playerId", "gameplayId", "gameId", "column", "row", "num", "symbol"})
public class StepDto {

    @JsonProperty("stepId")
    private Long id;

    @JsonProperty("playerId")
    @JacksonXmlProperty(isAttribute = true, localName = "playerId")
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
    @JacksonXmlProperty(isAttribute = true, localName = "num")
    private Integer num;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("text")
    @JacksonXmlText
    private String text;

    public StepDto(Step step) {
        this.id = step.getId();
        this.num = step.getNum();
        this.symbol = step.getSymbol();
        this.column = step.getColumn();
        this.row = step.getRow();
        this.playerId = step.getPlayer().getId();
        this.gameId = step.getGame().getId();
    }

    @JsonGetter("text")
    public String getText() {
        if (text == null) {
            if (row != null && column != null)
                text = row + " " + column;
        }
        return text;
    }

    @JsonSetter("text")
    private void setText(String text) {
        this.text = text;
    }

    @JsonSetter("playerId")
    private void setPlayerIdXML(String playerId) {
        this.playerId = Long.parseLong(playerId);
    }

    @JsonSetter("num")
    private void setNumXML(String num) {
        this.num = Integer.parseInt(num);
    }
}
