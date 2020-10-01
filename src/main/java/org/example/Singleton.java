package org.example;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Singleton {

    private static volatile Singleton instance;

    public static synchronized Singleton getInstance() {
        Singleton localInstance = instance;
        if (instance == null) {
            synchronized (Singleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Singleton();
                }
            }
        }
        return localInstance;
    }

    public void writeValueToFile(String value, String node) {

        Gson gson = new Gson();
        Map<String, Map<String, String>> map = FileChanger.readJsonFromFile();

        Map<String, String> map_obj;

        if (map != null && map.containsKey(node)) {
            map_obj = map.get(node);
            map_obj.put(String.valueOf(map_obj.size() + 1), value);
        } else {
            map_obj = new HashMap<>();
            map_obj.put("1", value);
        }

        map.put(node, map_obj);

        String str = gson.toJson(map);
        byte[] bytes = str.getBytes();

        FileChanger.writeToFile(bytes);

    }


}