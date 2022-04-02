package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.entities.Player;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "symbol"})
public class PlayerDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;

    public PlayerDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    public PlayerDto(Player player) {
        this.setPlayer(player);
    }

    @JsonIgnore
    public PlayerDto(Player player, String symbol) {
        this.setPlayer(player);
        this.symbol = symbol;
    }

    @JsonIgnore
    public void setPlayer(Player player) {
        if (player != null) {
            this.id = player.getId();
            this.name = player.getName();
        }
    }

    @JsonIgnore
    public Player getPlayer() {
        var player = new Player();
        player.setId(this.id);
        player.setName(this.name);
        return player;
    }
}
