package org.example;


class FirstSingletonThread extends BaseClass {

    public String getText() {
        String text = "";
        try {
            Thread.sleep(getRandomDelay(1000, 5000));
            text = "First class text";
        } catch (InterruptedException ex) {
            System.out.println("Sleep error");
        }
        return text;
    }

    public String getNode() {
        return "FirstNode";
    }
}