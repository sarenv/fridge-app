package ui.graphical;

import model.Food;
import model.Fridge;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphical.panels.AddPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FridgeUI extends JFrame {
    private static final String JSON_STORE = "./data/fridge.json";
    private Fridge fridge;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    JButton addButton;
    JButton removeButton;
    JButton saveButton;
    JButton loadButton;
    JButton viewButton;

    public FridgeUI() {
        super("Fridge Application");
        fridge = new Fridge();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initialiseGraphics();

    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initialiseGraphics() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createActions();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createActions() {
        JPanel actionArea = new JPanel();
        actionArea.setLayout(new GridLayout(0, 1));
        actionArea.setSize(new Dimension(0, 0));
        add(actionArea, BorderLayout.WEST);

        addButton = new JButton(new AddAction());
        removeButton = new JButton(new RemoveAction());
        saveButton = new JButton(new SaveAction());
        loadButton = new JButton(new LoadAction());
        viewButton = new JButton(new ViewAction());

        actionArea.add(addButton);
        actionArea.add(removeButton);
        actionArea.add(saveButton);
        actionArea.add(loadButton);
        actionArea.add(viewButton);

    }


    // EFFECTS: saves the fridge to file
    private void saveFridge() {
        try {
            jsonWriter.open();
            jsonWriter.write(fridge);
            jsonWriter.close();
            System.out.println("Saved Fridge" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads fridge from file
    private void loadFridge() {
        try {
            fridge = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private class AddAction extends AbstractAction {

        AddAction() {
            super("Add Food Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            String name = inputName();
            int size = inputSize();
            int daysBeforeExpire = inputDaysBeforeExpire();

            Food food = new Food(name, size, daysBeforeExpire);
            try {
                fridge.add(food);
                JOptionPane.showMessageDialog(null, name + " was added successfully!",
                        "Add Successful", JOptionPane.PLAIN_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private class RemoveAction extends AbstractAction {

        RemoveAction() {
            super("Remove a Food Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog(null, null, "Input Food Name",
                    JOptionPane.PLAIN_MESSAGE);
            try {
                fridge.removeFood(name);
                JOptionPane.showMessageDialog(null, name + " was removed successfully!",
                        "Remove Successful", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save the Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            saveFridge();
            JOptionPane.showMessageDialog(null, "Fridge saved Successfully!",
                    "Save Successful", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load a Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadFridge();
            JOptionPane.showMessageDialog(null, "Fridge loaded successfully!",
                    "Load Successful", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class ViewAction extends AbstractAction {

        ViewAction() {
            super("View the items in Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {


        }
    }

    // HELPERS for AddAction
    private int inputDaysBeforeExpire() {
        int daysBeforeExpire = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter the Food's Days Before Expiring",
                "Days Before Expire",
                JOptionPane.PLAIN_MESSAGE));

        while (daysBeforeExpire < 1) {
            JOptionPane.showMessageDialog(null, "DaysBeforeExpire must be greater than 0!",
                    "Add Unsuccessful!", JOptionPane.PLAIN_MESSAGE);
            daysBeforeExpire = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Enter the Food's Days Before Expiring",
                    "Days Before Expire",
                    JOptionPane.PLAIN_MESSAGE));
        }
        return daysBeforeExpire;
    }

    private int inputSize() {
        int size = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter the Food's Size",
                "Food Size",
                JOptionPane.PLAIN_MESSAGE));


        while (size > fridge.getFridgeSpace()) {
            JOptionPane.showMessageDialog(null, "Size too big for Fridge!",
                    "Add Unsuccessful!", JOptionPane.PLAIN_MESSAGE);
            size = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Enter the Food's Size",
                    "Food Size",
                    JOptionPane.PLAIN_MESSAGE));
        }

        return size;
    }

    private String inputName() {
        String name = JOptionPane.showInputDialog(
                null,
                "Enter the Food's Name",
                "Food Name",
                JOptionPane.PLAIN_MESSAGE);
        return name;
    }
}
