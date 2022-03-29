package io.ylab.ticTacToeGameRestApi.objects.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.ylab.ticTacToeGameRestApi.entities.Game;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.enums.GameType;
import io.ylab.ticTacToeGameRestApi.tools.Check;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
@JsonPropertyOrder({"id", "bordSize", "amountSymbolLine", "typeGame", "steps"})
public class GameJson {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("bordSize")
    private Integer bordSize;

    @JsonProperty("amountSymbolLine")
    private Integer amountSymbolLine;

    @JsonProperty("typeGame")
    private Integer typeGame;

    @JsonProperty("steps")
    private List<StepJson> steps;

    public GameJson(Game game) {
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
                    .map(StepJson::new)
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