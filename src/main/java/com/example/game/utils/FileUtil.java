package com.example.game.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * This util class handles the operations on a file
 *
 * @author khosro.makari@gmail.com
 */
public class FileUtil {

    public static final String BASE_PATH;

    private FileUtil() {
        throw new IllegalArgumentException();
    }

    /**
     * The path will be a "data" folder beside the project. First it gets the
     * path of jar file, then backwards two folders to reach the data folder in
     * the root path of the project
     */
    static {
        URL url = FileUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String path = url.getFile();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/"));
        BASE_PATH = path.concat("/data/");
    }

    /**
     * Appends a text to the end of the given file
     *
     * @param file to append text
     * @param str the text to be append to the end of the file
     * @throws IOException
     */
    public static void appendToFile(String file, String str) throws IOException {
        Files.write(Paths.get(BASE_PATH + File.separator + file), Arrays.asList(str), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    /**
     * reads all lines of the given file
     *
     * @param file to be read
     * @return the lines of the given file
     * @throws IOException
     */
    public static List<String> readLines(String file) throws IOException {
        return Files.readAllLines(Paths.get(BASE_PATH + File.separator + file));
    }

    /**
     * Updates file by truncating it, then inserting the given lines
     *
     * @param file to be updated
     * @param lines to be inserted
     * @throws IOException
     */
    public static void updateFile(String file, List<String> lines) throws IOException {
        Files.write(Paths.get(BASE_PATH + File.separator + file), lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
