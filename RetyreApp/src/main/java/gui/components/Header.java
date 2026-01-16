package gui.components;

import gui.utils.ColorPalette;
import gui.utils.RoundButton;
import gui.utils.ViewConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    private Dimension dimension;
    private GridLayout layout;
    private JButton addTypeButton;
    //private JButton searchButton;
    private JButton interventionButton;
    private JButton addVehicleButton;
    private JButton vehicleListButton;

    public Header(JPanel support, CardLayout layout){
        super();
        this.dimension = new Dimension(300,100);
        this.setBackground(ColorPalette.BACKGROUND_WHITE);
        this.setPreferredSize(this.dimension);
        this.layout = new GridLayout(1,6);
        this.setLayout(this.layout);
        this.addTypeButton = new RoundButton("Add Vehicle Type");
        //this.searchButton = new RoundButton("Search a vehicle");
        this.interventionButton = new RoundButton("Create an Intervention");
        this.addVehicleButton = new RoundButton("Add Vehicle");
        this.vehicleListButton = new RoundButton("Vehicle List");

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
        this.add(vehicleListButton);
        vehicleListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(support,ViewConstant.VEHICLELIST_VIEW);
            }
        });
        //this.add(searchButton);
        this.add((interventionButton));
        interventionButton.addActionListener(e -> {
            layout.show(support, ViewConstant.INTERVENTION_VIEW);
        });
    }
}
