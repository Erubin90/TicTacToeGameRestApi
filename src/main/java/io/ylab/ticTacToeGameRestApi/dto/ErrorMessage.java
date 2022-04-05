package io.ylab.ticTacToeGameRestApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.ylab.ticTacToeGameRestApi.utils.enums.ErrorType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"message", "errorCode"})
public class ErrorMessage {

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "errorCode")
    private Integer errorCode;

    public ErrorMessage(String message, ErrorType errorCode) {
        this.message = message;
        this.errorCode = errorCode.getCode();
    }
}
