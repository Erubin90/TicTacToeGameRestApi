package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.utils.enums.ErrorType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonPropertyOrder(value = {"message", "errorCode"})
public class Response<T> {

    @JsonProperty(value = "message")
    private T message;

    @JsonProperty(value = "errorCode")
    private Integer error;

    public Response(T message) {
        this.message = message;
    }

    public Response(T message, ErrorType errorType) {
        this.message = message;
        this.error = errorType.getCode();
    }
}
