package ui;


import model.Food;
import model.Fridge;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FridgeApp {
    private static final String JSON_STORE = "./data/fridge.json";
    private Fridge fridge;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS:runs the fridge application
    public FridgeApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        fridge = new Fridge();
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFridge();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFridge() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\n See you!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else if (command.equals("r")) {
            doRemove();
        } else if (command.equals("c")) {
            doContains();
        } else if (command.equals("e")) {
            doExpired();
        } else if (command.equals("v")) {
            doView();
        } else if (command.equals("s")) {
            saveFridge();
        } else if (command.equals("l")) {
            loadFridge();
        } else {
            System.out.println("Invalid selection!");
        }
    }

    // EFFECTS: displays all the options for users
    private void displayMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("\ta -> Add a new food item");
        System.out.println("\tr -> Remove the oldest food item");
        System.out.println("\tc -> Check if you have a food item in fridge");
        System.out.println("\te -> Throw all expired food out of fridge");
        System.out.println("\tv -> View the fridge contents");
        System.out.println("\ts -> Save the current fridge");
        System.out.println("\tl -> Load a fridge file");
        System.out.println("\tq -> Quit the fridge app");
    }


    // MODIFIES: this
    // EFFECTS: adds a food item to the fridge
    private void doAdd() {
        System.out.println("Please enter your food's information");
        System.out.println("What is the name of the food:");
        String name = input.next();
        name = name.toLowerCase();
        System.out.println("What is the size of the food:");
        int size = input.nextInt();
        System.out.println("How many days left before the food expires?");
        int daysBeforeExpire = input.nextInt();

        if (size <= 0) {
            System.out.println("Size must be greater than zero!");
        } else if (daysBeforeExpire <= 0) {
            System.out.println("Days before expiring has to be greater than zero!\n");
        } else {
            Food food = new Food(name, size, daysBeforeExpire);
            fridge.add(food);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a given item from the fridge with the smallest days to expire
    private void doRemove() {
        System.out.println("Enter the name of the food to remove");
        String name = input.next();
        name = name.toLowerCase();
        if (fridge.containsName(name)) {
            fridge.removeFood(name);
            System.out.println(name + " has been successfully removed from the fridge.");
        } else {
            System.out.println(name + " was not found.");
        }
    }

    // EFFECTS: check if fridge contains a user-inputted food item
    private void doContains() {
        System.out.println("What is the name of the food to check if the fridge contains?");
        String name = input.next();
        if (fridge.containsName(name)) {
            System.out.println("Yes! The fridge contains:" + name);
        } else {
            System.out.println("No! The fridge does not contain this item.");
        }
    }

    // EFFECTS: print the contents of the fridge
    private void doView() {
        ArrayList<Food> fridgeContent = fridge.getListOfFood();
        System.out.println("Fridge Space Left: " + fridge.getFridgeSpace());
        for (Food f : fridgeContent) {
            System.out.println(f.getName() + " - Expiring in " + f.getDaysBeforeExpire() + " days!");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove all expired food items from the fridge
    private void doExpired() {
        fridge.expired();
    }

    // EFFECTS: saves the fridge to file
    private void saveFridge() {
        try {
            jsonWriter.open();
            jsonWriter.write(fridge);
            jsonWriter.close();
            System.out.println("Saved Fridge" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads fridge from file
    private void loadFridge() {
        try {
            fridge = jsonReader.read();
            System.out.println("Loaded Fridge" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
