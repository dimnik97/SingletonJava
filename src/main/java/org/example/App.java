package org.example;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        FileChanger.clearFile();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            service.submit(new SecondSingletonThread());
            service.submit(new FirstSingletonThread());
        }
        service.shutdown();
    }


}
