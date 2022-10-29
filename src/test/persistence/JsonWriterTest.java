package persistence;

import model.Food;
import model.Fridge;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Fridge fridge = new Fridge();
            JsonWriter writer = new JsonWriter("./data/justan\0invalid:__fileName.json");
            writer.open();
            fail("Returned IO Exception as expected!");
        } catch (IOException e) {
            // test should pass
        }
    }

    @Test
    void testFridgeEmpty() {
        try {
            Fridge fridge = new Fridge();
            JsonWriter writer = new JsonWriter("./data/JsonWriterEmptyFridge.json");
            writer.open();
            writer.write(fridge);
            writer.close();

            JsonReader reader = new JsonReader("./data/JsonWriterEmptyFridge.json");
            fridge = reader.read();
            assertEquals(0, fridge.getListOfFood().size());
            assertEquals(Fridge.MAX_FRIDGE_SPACE, fridge.getFridgeSpace());
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

    @Test
    void testFridgeNormal() {
        try {
            Fridge testFridge = new Fridge();
            Food f1 = new Food("carrot",3,25);
            Food f2 = new Food("apple",1,36);
            Food f3 = new Food("peach",5,1);

            testFridge.add(f1);
            testFridge.add(f2);
            testFridge.add(f3);

            JsonWriter writer = new JsonWriter("./data/JsonWriterNormalFridge.json");
            writer.open();
            writer.write(testFridge);
            writer.close();

            JsonReader reader = new JsonReader("./data/JsonWriterNormalFridge.json");
            testFridge = reader.read();
            ArrayList<Food> listOfFood = testFridge.getListOfFood();
            assertEquals(3, testFridge.getListOfFood().size());
            assertEquals(91, testFridge.getFridgeSpace());
            checkFood("carrot",3,24, listOfFood.get(0));
            checkFood("apple",1,35, listOfFood.get(1));
            checkFood("peach",5,0, listOfFood.get(2));
        } catch (IOException e) {
            fail("IOException should not have been thrown!");
        }
    }

}
