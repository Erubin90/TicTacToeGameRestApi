package io.ylab.ticTacToeGameRestApi.utils;

import io.ylab.ticTacToeGameRestApi.dto.GameplayDto;
import io.ylab.ticTacToeGameRestApi.exceptions.InvalidFileDataException;
import io.ylab.ticTacToeGameRestApi.utils.enums.FileFormat;
import io.ylab.ticTacToeGameRestApi.utils.parsers.GameplayParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Convector {

    public static GameplayDto MultipartFileToGameplayDto(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();

        Check.isNullOrEmpty(fileName, "file name");

        int lastPointIndex = fileName.lastIndexOf(".");
        String format = fileName.substring(++lastPointIndex);
        var fileFormat = FileFormat.getFileFormat(format);
        GameplayParser parser;
        switch (fileFormat) {
            case JSON:
            case XML:
                parser = new GameplayParser(fileFormat);
                break;
            default:
                throw new InvalidFileDataException("The '" + format + "' file format is not supported");
        }
        GameplayDto gameplayDto;
        try (var input = multipartFile.getInputStream()) {
            var string = new String(input.readAllBytes());
            gameplayDto = parser.read(string);
        }
        catch (IOException e) {
            throw new InvalidFileDataException(e.getMessage());
        }

        var gameDto = gameplayDto.getGame();
        var stepAdapter = new StepAdapter(gameDto.getSteps());
        var stepDtoList = stepAdapter.adaptStepList();
        int boardSize = stepAdapter.getBoardSize();
        gameDto.setSteps(stepDtoList);
        gameDto.setBordSize(boardSize);

        return gameplayDto;
    }
}
