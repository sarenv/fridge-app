package persistence;

import model.Food;
import model.Fridge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Code based on by JsonSerialization Demo
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read fridge from JSON data stored in file
    public JsonReader(String source) {
        this.source = source;
    }

    public Fridge read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFridge(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses Fridge from JSON object and returns it
    private Fridge parseFridge(JSONObject jsonObject) {
        int fridgeSpace = jsonObject.getInt("fridgeSpace");
        Fridge fridge = new Fridge(fridgeSpace);
        addListOfFood(fridge, jsonObject);
        return fridge;
    }

    // MODIFIES: fridge
    // EFFECTS: parses listOfFood from JSON object and adds them to the Fridge
    private void addListOfFood(Fridge fridge, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfFood");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fridge, nextFood);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to the fridge
    private void addFood(Fridge fridge, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int foodSize = jsonObject.getInt("foodSize");
        int daysBeforeExpire = jsonObject.getInt("daysBeforeExpire");
        Food food = new Food(name, foodSize, daysBeforeExpire - 1);
        fridge.addFoodToList(food);
    }


}
