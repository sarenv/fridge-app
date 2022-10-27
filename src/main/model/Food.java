package model;

public class Food {
    private String name;
    private int size;
    private int daysBeforeExpire;

    // REQUIRES: foodType be of non-zero length, foodSize and daysToExpire be greater than zero
    public Food(String foodType, int foodSize, int daysToExpire) {
        name = foodType;
        size = foodSize;
        daysBeforeExpire = daysToExpire;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public int getDaysBeforeExpire() {
        return this.daysBeforeExpire;
    }
}