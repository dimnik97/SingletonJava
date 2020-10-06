package org.example;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Главный класс приложения
 * */
public class App {
    public static void main(String[] args) {
        FileChanger filechanger = new FileChanger("resources/file.json");
        filechanger.clearOrCreateFile();

        ExecutorService service = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            service.submit(new FirstSingletonThread());
            service.submit(new SecondSingletonThread());
        }
        service.shutdown();
    }
}
