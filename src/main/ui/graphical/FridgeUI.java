package ui.graphical;

import model.Food;
import model.Fridge;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.LogPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class FridgeUI extends JFrame {
    private static final String JSON_STORE = "./data/fridge.json";
    private Fridge fridge;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    JPanel spacePanel;
    JProgressBar fridgeSpaceBar;
    JLabel fridgeSpaceLabel;
    JLabel fridgeSpaceNumber;

    JButton addButton;
    JButton removeButton;
    JButton saveButton;
    JButton loadButton;
    JButton viewButton;

    JPanel viewPanel;

    // EFFECTS: Creates a FridgeUI with title "Fridge Application", a fridge, JSon methods and accompanied graphics
    public FridgeUI() {
        super("Fridge Application");
        fridge = new Fridge();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initialiseGraphics();

    }


    // MODIFIES: this
    // EFFECTS:  draws the JFrame in which this FridgeUI will operate and the accompanying panels and actions
    private void initialiseGraphics() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new LogPrinter());

        createSpacePanel();
        createActions();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: a helper which declares and initiates the fridge space progress bar
    private void createSpacePanel() {
        spacePanel = new JPanel();
        spacePanel.setLayout(new FlowLayout());

        fridgeSpaceLabel = new JLabel("Fridge Space Left:");
        spacePanel.add(fridgeSpaceLabel);

        fridgeSpaceBar = new JProgressBar(0,fridge.MAX_FRIDGE_SPACE);
        spacePanel.add(fridgeSpaceBar);
        fridgeSpaceBar.setString("FRIDGE IS FULL");

        fridgeSpaceNumber = new JLabel(Integer.toString(fridge.getFridgeSpace()));
        spacePanel.add(fridgeSpaceNumber);

        updateFridgeSpaceBar();

        add(spacePanel,BorderLayout.NORTH);

    }

    // EFFECTS: updates the fridgeSpaceBar and the fridgeSpaceNumber to the current value in fridge.getFridgeSpace()
    private void updateFridgeSpaceBar() {
        int fridgeSpace = 100 - fridge.getFridgeSpace();
        fridgeSpaceBar.setValue(fridgeSpace);
        fridgeSpaceNumber.setText(Integer.toString(fridge.getFridgeSpace()));

    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all actions
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

    // Represents the Action taken to add one Food to Fridge
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
                updateFridgeSpaceBar();
                JOptionPane.showMessageDialog(null, name + " was added successfully!",
                        "Add Successful", JOptionPane.PLAIN_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents the Action taken to remove a specific Food from Fridge
    private class RemoveAction extends AbstractAction {

        RemoveAction() {
            super("Remove a Food Item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog(null, null, "Input Food Name",
                    JOptionPane.PLAIN_MESSAGE);
            name = name.toLowerCase();
            try {
                int currentFridgeSpace = fridge.getFridgeSpace();
                fridge.removeFood(name);
                updateFridgeSpaceBar();
                if (currentFridgeSpace == (fridge.getFridgeSpace())) {
                    JOptionPane.showMessageDialog(null, "There is no " + name + " in the fridge!",
                            "Remove Successful", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, name + " was removed successfully!",
                            "Remove Successful", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Represents the Action taken to save the Fridge
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            saveFridge();
            updateFridgeSpaceBar();
            JOptionPane.showMessageDialog(null, "Fridge saved Successfully!",
                    "Save Successful", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Represents the Action taken to load up a saved Fridge
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadFridge();
            updateFridgeSpaceBar();
            JOptionPane.showMessageDialog(null, "Fridge loaded successfully!",
                    "Load Successful", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Represents the Action taken to view the contents of the Fridge
    private class ViewAction extends AbstractAction {

        ViewAction() {
            super("View the items in Fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            ArrayList<String> foods = fridge.viewFridgeContent();

            String[] foodR = new String[foods.size()];
            foods.toArray(foodR);
            add(new JList(foodR));
            pack();

        }
    }


    // HELPERS for AddAction

    // EFFECTS: Prompts the user to input daysBeforeExpire and makes sure daysBeforeExpire is > 0
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

    // EFFECTS: Prompts the user to input Size and makes sure that size < fridge.getFridgeSpace() and size > 0
    private int inputSize() {
        int size = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter the Food's Size",
                "Food Size",
                JOptionPane.PLAIN_MESSAGE));


        while (size > fridge.getFridgeSpace() || size < 1) {
            if (size < 1) {
                JOptionPane.showMessageDialog(null, "Size must be greater than zero!",
                        "Add Unsuccessful!", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Size too big for Fridge!",
                        "Add Unsuccessful!", JOptionPane.PLAIN_MESSAGE);
            }
            size = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Enter the Food's Size",
                    "Food Size",
                    JOptionPane.PLAIN_MESSAGE));
        }

        return size;
    }

    // EFFECTS: Prompts user to input the foodName
    private String inputName() {
        String name = JOptionPane.showInputDialog(
                null,
                "Enter the Food's Name",
                "Food Name",
                JOptionPane.PLAIN_MESSAGE);
        return name.toLowerCase();
    }
}
