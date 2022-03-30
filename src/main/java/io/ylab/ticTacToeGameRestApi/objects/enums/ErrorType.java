package io.ylab.ticTacToeGameRestApi.objects.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INVALID_VALUE(1),
    INVALID_EXECUTION(2);

    private final int code;
}
