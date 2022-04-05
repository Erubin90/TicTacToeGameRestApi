package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder(value = {"message", "error"})
public class Response<T> {

    @JsonProperty(value = "message")
    private T message;

    @JsonProperty(value = "error")
    private ErrorMessage error;

    public Response(T message) {
        this.message = message;
    }

    public Response(ErrorMessage error) {
        this.error = error;
    }
}
