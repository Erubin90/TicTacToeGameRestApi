package io.ylab.ticTacToeGameRestApi.utils.parsers;

import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.utils.enums.FileFormat;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class GameplayParser implements Parser<GameplayDto> {

    private Parser<GameplayDto> gameStorage;

    public GameplayParser(FileFormat fileFormat) {
        setFileFormat(fileFormat);
    }

    @Override
    public GameplayDto read(String string) throws IOException {
        return gameStorage.read(string);
    }

    @Override
    public GameplayDto read(File file) throws IOException {
        return gameStorage.read(file);
    }

    @Override
    public void write(GameplayDto game, File file) throws IOException {
        gameStorage.write(game, file);
    }

    public void setFileFormat(FileFormat format) {
        switch (format) {
            case XML:
                this.gameStorage = new GameplayXmlParser();
                break;
            case JSON:
                this.gameStorage = new GameplayJsonParser();
                break;
        }
    }
}
