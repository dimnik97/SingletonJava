package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс для работы с файлом
 * */
public class FileChanger {

    private String filePath = "";

    /**
     * @param path путь до файла
     */
    public FileChanger(String path) {
        this.filePath = path;
    }


    /**
     * Считывает содержимое файла filePath и парсит его в Map
     * @return возвращает Map
     */
    public Map readJsonFromFileAsMap() {

        Map map = new HashMap<>();

        Gson gson = new Gson();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            map = Optional.ofNullable(gson.fromJson(reader, Map.class)).orElseGet(HashMap::new);
            reader.close();
        } catch (Exception ex) {
            System.out.println("Nothing to read, wait");
        }
        return map;
    }

    /**
     * Записывает информацию в файл filePath
     * @param bytes записываемое значение
     */
    public void writeToFile(byte[] bytes) {
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("Write error");
        }
    }

    /**
     * Очищает файл, или создает, если его нет
     */
    public void clearOrCreateFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            try {
                Files.createFile(Paths.get(filePath));
                writer = new PrintWriter(filePath);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }
}
