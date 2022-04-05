package io.ylab.ticTacToeGameRestApi.utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.dto.Response;
import io.ylab.ticTacToeGameRestApi.utils.parsers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GameplayJsonParser implements Parser<GameplayDto> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public GameplayDto read(String string) throws IOException {
        int beginIndex = string.indexOf(":") + 1;
        int endIndex = string.length() - 1;
        String json = string.substring(beginIndex, endIndex);
        return OBJECT_MAPPER.readValue(json, GameplayDto.class);
    }

    @Override
    public GameplayDto read(File file) throws IOException {
        try (var input = new FileInputStream(file)) {
            var string = new String(input.readAllBytes());
            return read(string);
        }
    }

    @Override
    public void write(GameplayDto gameplay, File file) throws IOException {
        OBJECT_MAPPER.writeValue(file, new Response<>(gameplay));
    }
}
