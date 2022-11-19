package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


public class Fridge implements Writable {
    public static final int MAX_FRIDGE_SPACE = 100;
    private ArrayList<Food> listOfFood;
    private int fridgeSpace;

    // EFFECTS: Create fridge with fridgeSpace as MAX_FRIDGE_SPACE and a list of food
    public Fridge() {
        fridgeSpace = MAX_FRIDGE_SPACE;
        listOfFood = new ArrayList<>();

    }

    // EFFECTS: Create a fridge with a given fridgeSpace and a list of food
    public Fridge(int size) {
        fridgeSpace = size;
        listOfFood = new ArrayList<>();
    }

    //getter
    public ArrayList<Food> getListOfFood() {
        return this.listOfFood;
    }

    public int getFridgeSpace() {
        return this.fridgeSpace;
    }

    // MODIFIES: this
    // EFFECTS:adds food to the listOfFood
    public void addFoodToList(Food food) {
        listOfFood.add(food);
    }

    // MODIFIES: this
    // EFFECTS: add given food to the listOfFood in fridge and reduce fridge space by the size of the food
    public void add(Food food) {
        if (food.getFoodSize() <= fridgeSpace) {
            addFoodToList(food);
            fridgeSpace -= food.getFoodSize();
        }
    }


    // REQUIRES: fridge is not empty
    // MODIFIES: this
    // EFFECTS: remove the first food with given food name found. If not there, nothing happens.
    public void removeFood(String foodName) {
        int count = -1;
        int index = -1;
        for (Food f : listOfFood) {
            count++;
            if (f.getName().equals(foodName)) {
                index = count;
            }
        }
        if (index > -1) {
            Food foodToRemove = listOfFood.get(index);
            fridgeSpace += foodToRemove.getFoodSize();
            listOfFood.remove(foodToRemove);
        }

    }

    // EFFECTS: returns true if fridge contains a given food and false otherwise.
    public boolean containsName(String foodName) {
        for (Food f : listOfFood) {
            if (f.getName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: check if a food item is expired. If it is, throw it away.
    public void expired() {
        int freedSpace = 0;
        Food chosenFood;
        int listLength = listOfFood.size();

        for (int i = 0; i < listLength; i++) {
            chosenFood = listOfFood.get(i);
            if (chosenFood.getDaysBeforeExpire() <= 0) {
                freedSpace += chosenFood.getFoodSize();
                listOfFood.remove(i);
                listLength--;
                i--;
            }
        }
        fridgeSpace += freedSpace;
    }

    @Override
    // EFFECTS: returns the Fridge as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfFood", fridgeToJson());
        json.put("fridgeSpace", fridgeSpace);
        return json;
    }

    // EFFECTS: returns the food in this fridge as a JSON array
    private JSONArray fridgeToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : listOfFood) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}

