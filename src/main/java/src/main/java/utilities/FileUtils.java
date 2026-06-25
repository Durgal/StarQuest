package src.main.java.utilities;

import src.main.java.game.data.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static BufferedReader getFileHandler(String file) {
        InputStream is = ResourceLoader.class.getResourceAsStream(file);
        InputStreamReader sr = new InputStreamReader(is);
        return new BufferedReader(sr);
    }

    public static void writeToFile(String file, String[] content) throws IOException {
        Path filePath = Path.of(file);
        List<String> lines = Arrays.asList(content);
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }

    public static void appendToFile(Path path, String content)
            throws IOException {

        Files.writeString(path,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

    }

}
