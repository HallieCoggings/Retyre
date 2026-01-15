package gui.panel;

import gui.utils.BackButton;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

public class AddVehiclePanel extends JPanel {
    private BackButton backButton;
    private LayoutManager layout;
    private JButton saveButton;
    private QueryRetyre server;

    //Field for form
    private JTextField typeV;
    private JTextField mileage;
    private JTextField licencePlate;
    private JTextField dateCirculation;
    private JTextField name;
    private JTextField fName;
    private JTextField personalDetails;

    public AddVehiclePanel(QueryRetyre server,JPanel support, CardLayout layout){
        super();
        this.server = server;
        this.backButton = new BackButton(support,layout);
        this.saveButton = new JButton("Add this vehicle");
        JLabel titlePane = new JLabel("Panel to add a vehicle and its owner");

        // Set layout
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);
        GridBagConstraints gbc = new GridBagConstraints();

        //Set Field Form
        //label
        JLabel typeLab = new JLabel("Vehicle Type :");
        JLabel mileageLab = new JLabel("Mileage :");
        JLabel lpLab = new JLabel("Licence Plate :");
        JLabel dateLab = new JLabel("Date of circulation :");
        JLabel nameLab = new JLabel("Owner's name :");
        JLabel fNameLab = new JLabel("Owner's first name :");
        JLabel perDetLab = new JLabel("Owner's personal details");

        Font font = new Font("Arial",Font.BOLD,18);
        Font fontField = new Font("Arial",Font.PLAIN,18);
        typeLab.setFont(font);mileageLab.setFont(font);lpLab.setFont(font);dateLab.setFont(font);
        nameLab.setFont(font);fNameLab.setFont(font);perDetLab.setFont(font);

        //Set Field
        this.typeV = new JTextField(20);
        this.mileage = new JTextField(20);
        this.licencePlate = new JTextField(20);
        this.dateCirculation = new JTextField(20);
        this.name = new JTextField(20);
        this.fName = new JTextField(20);
        this.personalDetails = new JTextField(20);


        //Set font
        this.typeV.setFont(fontField);this.mileage.setFont(fontField);this.licencePlate.setFont(fontField);
        this.dateCirculation.setFont(fontField);this.name.setFont(fontField);this.fName.setFont(fontField);
        this.personalDetails.setFont(fontField);

        //-- Mise en place
        gbc.insets = new Insets(50,50,50,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // - Line 0
        gbc.gridx = 0; //Col 0
        gbc.gridy = 0; //Row 0
        gbc.gridwidth = 1; // element takes one column
        this.add(backButton,gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(50,250,50,50);
        this.add(titlePane,gbc);

        gbc.insets = new Insets(50,50,50,50);
        gbc.gridwidth = 1;


        // - Line 1
        gbc.gridy = 1;
        gbc.gridx = 0; this.add(nameLab,gbc);
        gbc.gridx = 1; this.add(this.name,gbc);
        gbc.gridx = 2; this.add(fNameLab,gbc);
        gbc.gridx = 3; this.add(this.fName,gbc);
        gbc.gridx = 4; this.add(perDetLab,gbc);
        gbc.gridx = 5; this.add(this.personalDetails,gbc);

        // - Line 2
        gbc.gridy = 2;
        gbc.gridx = 0; this.add(typeLab,gbc);
        gbc.gridx = 1; this.add(this.typeV,gbc);
        gbc.gridx = 2; this.add(lpLab,gbc);
        gbc.gridx = 3; this.add(this.licencePlate,gbc);

        // - Line 3
        gbc.gridy = 3;
        gbc.gridx = 0; this.add(mileageLab,gbc);
        gbc.gridx = 1; this.add(this.mileage,gbc);
        gbc.gridx = 2; this.add(dateLab,gbc);
        gbc.gridx = 3; this.add(this.dateCirculation,gbc);

        //Line 4
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        saveButton.setPreferredSize(new Dimension(500,70));
        this.add(saveButton,gbc);



        this.setPreferredSize(new Dimension(1920,1080));
    }
}
