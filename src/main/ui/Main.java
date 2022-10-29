package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new FridgeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the fridge application: File not found!");
        }
    }
}
