package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Food class
class FoodTest {
    private  Food testFood;
    @BeforeEach
    void runBefore() {testFood = new Food("yam",10,99);}

    @Test
    void TestConstructor() {
        assertEquals("yam",testFood.getName());
        assertEquals(10,testFood.getFoodSize());
        assertEquals(99,testFood.getDaysBeforeExpire());
    }

}