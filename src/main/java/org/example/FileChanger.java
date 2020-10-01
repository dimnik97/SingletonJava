package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileChanger {
    private static final String filename = "file.json";

    public static Map readJsonFromFile() {

        Map map = new HashMap<>();

        Gson gson = new Gson();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            map = Optional.ofNullable(gson.fromJson(reader, Map.class)).orElseGet(HashMap::new);
            reader.close();
        } catch (Exception ex) {
            System.out.println("Nothing to read, wait");
        }

        return map;
    }

    public static void writeToFile(byte[] bytes) {

        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("Write error");
        }

    }

    public static void clearFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            try {
                Files.createFile(Paths.get(filename));
                writer = new PrintWriter(filename);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }
}
