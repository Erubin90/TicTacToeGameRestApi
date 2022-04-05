package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.ylab.ticTacToeGameRestApi.model.Gameplay;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "GamePlay")
@JsonPropertyOrder({"Player", "Game", "GameResult", "gameplayId", "playerId", "players", "playerSymbol", "game", "gameResult"})
public class GameplayDto {

    @JsonProperty("gameplayId")
    private Long id;

    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("players")
    @JacksonXmlProperty(localName = "Player")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PlayerDto> players;

    @JsonProperty("playerSymbol")
    private String symbol;

    @JsonProperty("game")
    @JacksonXmlProperty(localName = "Game")
    private GameDto game;

    @JsonProperty("gameResult")
    @JacksonXmlProperty(localName = "GameResult")
    private GameResultDto gameResult;

    @JsonIgnore
    public GameplayDto(Gameplay gameplay) {
        this.setGameplay(gameplay);
    }

    @JsonIgnore
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
        this.gameResult = new GameResultDto(gameplay.getGameResult());
        this.symbol = null;
        this.playerId = null;
    }
}
