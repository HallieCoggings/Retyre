package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    private Dimension dimension;
    private GridLayout layout;
    private JButton addButton;
    private JButton searchButton;
    private JButton interventionButton;

    public Header(JPanel support, CardLayout layout){
        super();
        this.dimension = new Dimension(300,100);
        this.setBackground(Color.green);
        this.setPreferredSize(this.dimension);
        this.layout = new GridLayout(1,4);
        this.setLayout(this.layout);
        this.addButton = new JButton("Add Vehicle");
        this.searchButton = new JButton("Search a vehicle");
        this.interventionButton = new JButton("Create an Intervnetion");

        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(support,"addVT_Pan");
            }
        });
        this.add(addButton);
        this.add(searchButton);
        this.add((interventionButton));
    }
}
