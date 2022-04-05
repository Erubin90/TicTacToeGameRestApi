package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.ylab.ticTacToeGameRestApi.model.Player;
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
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private Long id;

    @JsonProperty("name")
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    @JsonProperty("symbol")
    @JacksonXmlProperty(isAttribute = true, localName = "symbol")
    private String symbol;

    @JsonIgnore
    public PlayerDto(String name) {
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
}
