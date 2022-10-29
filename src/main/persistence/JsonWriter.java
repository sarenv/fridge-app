package persistence;

import model.Fridge;
import org.json.JSONObject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Code based on by JsonSerialization Demo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throw FileNotFoundException if destination file is not found
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of the Fridge to file
    public void write(Fridge f) {
        JSONObject json = f.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes the json string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
