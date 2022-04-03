package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.ylab.ticTacToeGameRestApi.utils.enums.GameType;
import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Step", "id", "bordSize", "amountSymbolLine", "typeGame", "steps"})
public class GameDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("bordSize")
    private Integer bordSize;

    @JsonProperty("amountSymbolLine")
    private Integer amountSymbolLine;

    @JsonProperty("typeGame")
    private Integer typeGame;

    @JsonProperty("steps")
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

    @JsonSetter("bordSize")
    public void setBordSize(Integer bordSize) {
        Check.isNull(bordSize, "bordSize");
        if (bordSize <= 10 && bordSize > 2) {
            this.bordSize = bordSize;
        }
        else {
            throw new InvalidValueException("invalid bordSize");
        }
    }

    @JsonSetter("amountSymbolLine")
    public void setAmountSymbolLine(Integer amountSymbolLine) {
        Check.isNull(amountSymbolLine, "amountSymbolLine");
        if (amountSymbolLine <= this.bordSize && amountSymbolLine > 2)
            this.amountSymbolLine = amountSymbolLine;
        else
            throw new InvalidValueException("invalid amountSymbolLine");
    }

    @JsonSetter("typeGame")
    public void setTypeGame(Integer typeGame) {
        Check.isNull(typeGame, "typeGame");
        var type = GameType.getTypeGame(typeGame);
        if (type != GameType.UNKNOWN)
            this.typeGame = typeGame;
        else
            throw new InvalidValueException("invalid typeGame");
    }
}
