package io.ylab.ticTacToeGameRestApi.utils.parsers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.utils.parsers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameplayXmlParser implements Parser<GameplayDto> {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @Override
    public GameplayDto read(String string) throws IOException {
        return XML_MAPPER.readValue(string, GameplayDto.class);
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
        try (var outputStream = new FileOutputStream(file)) {
            String heading = "<?xml version='1.0' encoding='windows-1251'?>\n";
            String content = XML_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(gameplay);
            outputStream.write(heading.getBytes());
            outputStream.write(content.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
