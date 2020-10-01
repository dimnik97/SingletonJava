package org.example;

public abstract class BaseClass implements Runnable {

    abstract String getNode();

    abstract String getText();

    public static int getRandomDelay(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void run() {

        Singleton singleton = Singleton.getInstance();

        singleton.writeValueToFile(this.getText(), this.getNode());

    }
}
