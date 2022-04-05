package io.ylab.ticTacToeGameRestApi.utils.parsers;

import java.io.File;
import java.io.IOException;

public interface Parser<T> {

    T read(String string) throws IOException;

    T read(File file) throws IOException;

    void write(T t, File file) throws IOException;
}
