package io.ylab.ticTacToeGameRestApi.objects.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"player_id"})
public class PlayerRequest {

    @JsonProperty("player_id")
    private Long id;
}
