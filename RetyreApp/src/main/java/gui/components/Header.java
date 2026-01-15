package gui.components;

import gui.utils.ViewConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    private Dimension dimension;
    private GridLayout layout;
    private JButton addTypeButton;
    private JButton searchButton;
    private JButton interventionButton;
    private JButton addVehicleButton;

    public Header(JPanel support, CardLayout layout){
        super();
        this.dimension = new Dimension(300,100);
        this.setBackground(Color.green);
        this.setPreferredSize(this.dimension);
        this.layout = new GridLayout(1,5);
        this.setLayout(this.layout);
        this.addTypeButton = new JButton("Add Vehicle Type");
        this.searchButton = new JButton("Search a vehicle");
        this.interventionButton = new JButton("Create an Intervention");
        this.addVehicleButton = new JButton("Add Vehicle");

        this.addTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(support, ViewConstant.ADDVT_VIEW);
            }
        });
        this.add(addTypeButton);
        this.add(addVehicleButton);
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(support,ViewConstant.ADDV_VIEW);
            }
        });
        this.add(searchButton);
        this.add((interventionButton));
    }
}
