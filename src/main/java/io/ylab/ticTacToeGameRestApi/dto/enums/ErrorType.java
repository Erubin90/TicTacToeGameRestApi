package io.ylab.ticTacToeGameRestApi.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INVALID_VALUE(1),
    INVALID_EXECUTION(2),
    DONT_MACH_VALUE(3);

    private final int code;
}
