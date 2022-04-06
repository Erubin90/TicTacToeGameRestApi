package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.ylab.ticTacToeGameRestApi.model.Game;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Step", "step", "id", "bordSize", "amountSymbolLine", "typeGame"})
public class GameDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("bordSize")
    private Integer bordSize;

    @JsonProperty("amountSymbolLine")
    private Integer amountSymbolLine;

    @JsonProperty("typeGame")
    private Integer typeGame;

    @JsonProperty("step")
    @JacksonXmlProperty(localName = "Step")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<StepDto> steps;

    public GameDto(Game game) {
        this.setGame(game);
    }

    public void setGame(Game game) {
        this.id = game.getId();
        this.bordSize = game.getBordSize();
        this.amountSymbolLine = game.getAmountSymbolLine();
        this.typeGame = game.getTypeGame();
        var gameStepList = game.getSteps();
        if (gameStepList != null) {
            this.steps = gameStepList.stream()
                    .map(StepDto::new)
                    .collect(Collectors.toList());
        }
    }
}
