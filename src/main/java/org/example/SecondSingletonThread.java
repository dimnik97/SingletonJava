package org.example;

/**
 * Класс для запуска в потоке
 */
class SecondSingletonThread extends BaseClass {

    public String getText() {
        String text = "";
        try {
            Thread.sleep(getRandomDelay(1000, 5000));
            text = "Second class text";
        } catch (InterruptedException ex) {
            System.out.println("Sleep error");
        }
        return text;
    }

    public String getNode() {
        return "SecondNode";
    }
}