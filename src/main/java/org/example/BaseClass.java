package org.example;

public abstract class BaseClass implements Runnable {

    /**
     * @return Возвращает статичный узел для генерации json
     */
    abstract String getNode();

    /**
     * @return Возвращает статичный текст со случайной задержкой
     */
    abstract String getText();

    /**
     * Генерирует число в указанном диапазоне
     * @param min нижняя граница
     * @param max верхняя граница
     * @return возвращает случайное число (int)
     */
    public static int getRandomDelay(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Использует Singleton для записи текста в файл
     * */
    public void run() {

        Singleton singleton = Singleton.getInstance();

        singleton.writeValueToFile(this.getText(), this.getNode());

    }
}
