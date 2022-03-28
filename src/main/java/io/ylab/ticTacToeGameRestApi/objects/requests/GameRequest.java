package io.ylab.ticTacToeGameRestApi.objects.requests;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.ylab.ticTacToeGameRestApi.excrptions.InvalidValueException;
import io.ylab.ticTacToeGameRestApi.objects.enums.GameType;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "bordSize", "amountSymbolLine", "typeGame"})
public class GameRequest {

    private Long id;

    private Integer bordSize;

    private Integer amountSymbolLine;

    private Integer typeGame;

    @JsonSetter("bordSize")
    public void setBordSize(Integer bordSize) {
        if (bordSize <= 10 && bordSize > 2) {
            this.bordSize = bordSize;
        }
        else {
            throw new InvalidValueException("invalid bordSize");
        }
    }

    @JsonSetter("amountSymbolLine")
    public void setAmountSymbolLine(Integer amountSymbolLine) {
        if (amountSymbolLine <= this.bordSize && amountSymbolLine > 2)
            this.amountSymbolLine = amountSymbolLine;
        else
            throw new InvalidValueException("invalid amountSymbolLine");
    }

    @JsonSetter("typeGame")
    public void setTypeGame(Integer typeGame) {
        var type = GameType.getTypeGame(typeGame);
        if (type != GameType.UNKNOWN)
            this.typeGame = typeGame;
        else
            throw new InvalidValueException("invalid typeGame");
    }
}
