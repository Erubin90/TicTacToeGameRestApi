package io.ylab.ticTacToeGameRestApi.objects.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameType {
    PLAYER_PLAYER(1),
    BOT_PLAYER(2),
    UNKNOWN(-1);

    private final int num;


    public static GameType getTypeGame(int num) {
        var typeGame = UNKNOWN;
        switch (num) {
            case 1:
                typeGame = PLAYER_PLAYER;
                break;
            case 2:
                typeGame = BOT_PLAYER;
                break;
        }
        return typeGame;
    }
}
