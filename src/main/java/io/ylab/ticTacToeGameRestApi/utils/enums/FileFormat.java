package io.ylab.ticTacToeGameRestApi.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileFormat {
    XML("xml"),
    JSON("json"),
    NO_CORRECT("");

    private final String format;

    public static FileFormat getFileFormat(String format) {
        if (JSON.format.equals(format)) {
            return JSON;
        }
        else if (XML.format.equals(format)) {
            return XML;
        }
        else {
            return NO_CORRECT;
        }
    }

    @Override
    public String toString() {
        return format;
    }
}
