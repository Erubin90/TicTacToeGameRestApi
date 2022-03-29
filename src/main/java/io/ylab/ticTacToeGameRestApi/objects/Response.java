package io.ylab.ticTacToeGameRestApi.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"message", "errorCode"})
public class Response<T> {

    @JsonProperty(value = "message")
    private T message;

    @JsonProperty(value = "errorCode")
    private Integer error;

    public Response(T message) {
        this.message = message;
    }
}
