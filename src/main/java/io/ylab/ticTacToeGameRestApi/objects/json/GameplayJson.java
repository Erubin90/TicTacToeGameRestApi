package io.ylab.ticTacToeGameRestApi.objects.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.Gameplay;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"gameplayId", "playerId", "players", "playerSymbol", "game", "gameResult"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameplayJson {

    @JsonProperty("gameplayId")
    private Long id;

    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("players")
    private List<PlayerJson> players;

    @JsonProperty("playerSymbol")
    private String symbol;

    @JsonProperty("game")
    private GameJson game;

    @JsonProperty("gameResult")
    private GameResultJson gameResult;

    public GameplayJson(Gameplay gameplay) {
        this.setGameplay(gameplay);
    }

    public void setGameplay(Gameplay gameplay) {
        var gameplayPlayerList = gameplay.getGameplayPlayerList();
        if (gameplayPlayerList != null) {
            this.players = gameplayPlayerList
                    .stream()
                    .map(x -> new PlayerJson(x.getPlayer(), x.getSymbol()))
                    .collect(Collectors.toList());
        }
        this.game = new GameJson(gameplay.getGame());
        this.gameResult = new GameResultJson(gameplay.getResult());
    }
}
