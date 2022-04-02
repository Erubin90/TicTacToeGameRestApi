package io.ylab.ticTacToeGameRestApi.dto;

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
public class GameplayDto {

    @JsonProperty("gameplayId")
    private Long id;

    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("players")
    private List<PlayerDto> players;

    @JsonProperty("playerSymbol")
    private String symbol;

    @JsonProperty("game")
    private GameDto game;

    @JsonProperty("gameResult")
    private GameResultDto gameResult;

    public GameplayDto(Gameplay gameplay) {
        this.setGameplay(gameplay);
    }

    public void setGameplay(Gameplay gameplay) {
        this.id = gameplay.getId();
        var gameplayPlayerList = gameplay.getGameplayPlayerList();
        if (gameplayPlayerList != null) {
            this.players = gameplayPlayerList
                    .stream()
                    .map(x -> new PlayerDto(x.getPlayer(), x.getSymbol()))
                    .collect(Collectors.toList());
        }
        this.game = new GameDto(gameplay.getGame());
        this.gameResult = new GameResultDto(gameplay.getResult());
        this.symbol = null;
    }
}
