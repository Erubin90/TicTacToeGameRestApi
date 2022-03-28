package io.ylab.ticTacToeGameRestApi.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"message", "error"})
public class Response<T> {

    @JsonProperty(value = "message")
    private T message;

    @JsonProperty(value = "error")
    private Error error;
}
