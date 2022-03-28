package io.ylab.ticTacToeGameRestApi.objects;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.ylab.ticTacToeGameRestApi.objects.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    @JsonProperty("code")
    private ErrorType code;

    @JsonProperty("errorMessage")
    private String message;
}
