package org.example;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий паттерн singleton
 */
public class Singleton {

    private static volatile Singleton instance;

    /**
     * @return возвращает instance экземпляра класса
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    /**
     * Генерирует json и записывает его в файл
     *
     * @param value текст, который нужно добавить к json узлу
     * @param node узел
     */
    public void writeValueToFile(String value, String node) {

        Gson gson = new Gson();

        FileChanger fileChanger = new FileChanger("resources/file.json");

        Map<String, Map<String, String>> map = fileChanger.readJsonFromFileAsMap();

        Map<String, String> mapObj;

        if (map != null && map.containsKey(node)) {
            mapObj = map.get(node);
            mapObj.put(String.valueOf(mapObj.size() + 1), value);
        } else {
            mapObj = new HashMap<>();
            mapObj.put("1", value);
        }
        map.put(node, mapObj);

        byte[] bytes = gson.toJson(map).getBytes();

        fileChanger.writeToFile(bytes);
    }
}