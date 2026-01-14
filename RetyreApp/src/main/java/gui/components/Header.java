package gui.components;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private Dimension dimension;
    private GridLayout layout;
    private JButton addButton;
    private JButton searchButton;
    private JButton interventionButton;

    public Header(){
        super();
        this.dimension = new Dimension(300,100);
        this.setBackground(Color.green);
        this.setPreferredSize(this.dimension);
        this.layout = new GridLayout(1,4);
        this.setLayout(this.layout);
        this.addButton = new JButton("Add Vehicle");
        this.searchButton = new JButton("Search a vehicle");
        this.interventionButton = new JButton("Create an Intervnetion");

        //this.addButton.addActionListener(); //TODO : Regarder comment implémente les actions listeners

        this.add(addButton);
        this.add(searchButton);
        this.add((interventionButton));
    }
}
