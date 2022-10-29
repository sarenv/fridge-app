package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String name,int foodSize,int daysBeforeExpire,Food food) {
        assertEquals(name,food.getName());
        assertEquals(foodSize,food.getFoodSize());
        assertEquals(daysBeforeExpire,food.getDaysBeforeExpire());
    }
}
