package ui.graphical.panels;

import javax.swing.*;

public class AddPanel extends JPanel {

    public AddPanel() {
        setLayout(null);

        JLabel nameLabel = new JLabel("Food Name:");
        nameLabel.setBounds(10,20,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField();
        nameText.setBounds(100,20,165,25);
        add(nameText);

        JLabel sizeLabel = new JLabel("Food Size:");
        nameLabel.setBounds(10,50,80,25);
        add(sizeLabel);

        JTextField sizeText = new JTextField();
        sizeText.setBounds(100,50,165,25);
        add(sizeText);

        JLabel daysLabel = new JLabel("Days Before Expire:");
        nameLabel.setBounds(10,80,80,25);
        add(daysLabel);

        JTextField daysText = new JTextField();
        daysText.setBounds(100,50,165,25);
        add(daysText);
    }


}
