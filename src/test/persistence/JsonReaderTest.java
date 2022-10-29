package persistence;

import model.Food;
import model.Fridge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/DoesNotExist.json");
        try {
            Fridge testFridge = reader.read();
            fail("Returned IO Exception as expected!");
        } catch (IOException e) {
            // test should pass
        }
    }

    @Test
    void testEmptyFridge() {
        JsonReader reader = new JsonReader("./data/JsonReaderEmptyFridge.json");
        try {
            Fridge testFridge = reader.read();
            assertEquals(0, testFridge.getListOfFood().size());
            assertEquals(Fridge.MAX_FRIDGE_SPACE, testFridge.getFridgeSpace());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    void testNormalFridge() {
        JsonReader reader = new JsonReader("./data/JsonReaderNormalFridge.json");
        try {
            Fridge testFridge = reader.read();
            ArrayList<Food> listOfFood = testFridge.getListOfFood();
            assertEquals(3, testFridge.getListOfFood().size());
            assertEquals(58, testFridge.getFridgeSpace());
            checkFood("pizza", 25, 14, listOfFood.get(0));
            checkFood("milk", 15, 4, listOfFood.get(1));
            checkFood("apple", 2, 19, listOfFood.get(2));
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }

    }

}
