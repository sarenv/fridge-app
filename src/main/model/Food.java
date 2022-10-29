package model;

import org.json.JSONObject;
import persistence.Writable;

public class Food implements Writable {
    private String name;
    private int foodSize;
    private int daysBeforeExpire;

    // REQUIRES: foodType be of non-zero length, foodSize and daysToExpire be greater than zero
    public Food(String foodType, int foodSize, int daysToExpire) {
        name = foodType;
        this.foodSize = foodSize;
        daysBeforeExpire = daysToExpire;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getFoodSize() {
        return this.foodSize;
    }

    public int getDaysBeforeExpire() {
        return this.daysBeforeExpire;
    }

    @Override
    // EFFECTS: returns the Food as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("foodSize", foodSize);
        json.put("daysBeforeExpire",daysBeforeExpire);
        return json;
    }
}