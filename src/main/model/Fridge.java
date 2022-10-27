package model;

import java.util.ArrayList;


public class Fridge {
    public static final int MAX_CAPACITY = 100;
    private ArrayList<Food> fridge;
    private int capacity;

    public Fridge() {

        fridge = new ArrayList<>();
        capacity = MAX_CAPACITY;
    }

    //getter
    public ArrayList<Food> getFridge() {
        return this.fridge;
    }

    public int getCapacity() {
        return this.capacity;
    }


    // MODIFIES: this
    // EFFECTS: add given food to the fridge
    public void add(Food food) {
        if (capacity == 0 || food.getSize() > capacity) {
            System.out.println("The fridge does not have any space left!");
        } else {
            fridge.add(food);
            capacity -= food.getSize();
        }
    }

    // REQUIRES: fridge is not empty
    // MODIFIES: this
    // EFFECTS: remove the first food with given food name found. If not there, nothing happens.
    public void remove(String foodName) {
        fridge.stream()
                .filter(food -> food.getName().equals(foodName))
                .findFirst()
                .ifPresent(fridge::remove);

    }

    // EFFECTS: returns true if fridge contains a given food and false otherwise.
    public boolean containsName(String foodName) {
        for (Food f : fridge) {
            if (f.getName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: check if a food item is expired. If it is, throw it away.
    /*public void hasExpired() {
        for (Food f: fridge) {
            if (f.getDaysBeforeExpire() <= 0) {
                fridge.remove(f);
            }
        }
    }*/
}

