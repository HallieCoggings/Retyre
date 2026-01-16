package gui.panel;

import gui.utils.BackButton;
import gui.utils.ColorPalette;
import gui.utils.RoundButton;
import model.*;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class InterventionPanel extends JPanel {
    private BackButton backButton;
    private LayoutManager layout;
    private RoundButton saveButton;
    private QueryRetyre server;

    /* ------- FORM FIELDS ------- */
    private JComboBox<Vehicle> vehicleCombo;
    private JComboBox<String> typeCombo;
    private JTextField employeeField;
    private JSpinner dateSpinner;
    private JTextField priceField;
    private JTextArea commentsArea;
    private JCheckBox urgentCheckBox;

    /* ------- DATA ------- */
    private List<Vehicle> vehicles;
    private List<InterventionType> types;

    public InterventionPanel(QueryRetyre server, JPanel support, CardLayout layout){
        super();
        this.server = server;
        this.backButton = new BackButton(support, layout);
        this.saveButton = new RoundButton("Create this intervention");

        /* ------- TITLE ------- */
        JLabel titlePane = new JLabel("Panel to create an intervention");
        titlePane.setFont(new Font("Arial", Font.BOLD, 24));
        titlePane.setForeground(ColorPalette.DARK_BLUE);

        /* ------- LOAD DATA ------- */
        loadData();

        /* ------- SET LAYOUT ------- */
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);
        this.setBackground(ColorPalette.BACKGROUND_LIGHT);
        GridBagConstraints gbc = new GridBagConstraints();

        /* ------- LABELS ------- */
        JLabel vehicleLabel = new JLabel("Vehicle * :");
        JLabel typeLabel = new JLabel("Intervention Type * :");
        JLabel employeeLabel = new JLabel("Employee :");
        JLabel dateLabel = new JLabel("Date * :");
        JLabel priceLabel = new JLabel("Estimated Price :");
        JLabel commentsLabel = new JLabel("Comments :");
        JLabel msgSuccess = new JLabel("Intervention successfully created!");

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);

        vehicleLabel.setFont(labelFont);
        typeLabel.setFont(labelFont);
        employeeLabel.setFont(labelFont);
        dateLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);
        commentsLabel.setFont(labelFont);
        msgSuccess.setFont(new Font("Arial", Font.BOLD, 18));
        msgSuccess.setForeground(ColorPalette.GREEN_OK);

        /* ------- VEHICLE COMBO ------- */
        this.vehicleCombo = new JComboBox<>();
        this.vehicleCombo.setFont(fieldFont);
        this.vehicleCombo.setPreferredSize(new Dimension(350, 40));

        for(Vehicle v : vehicles){
            this.vehicleCombo.addItem(v);
        }

        this.vehicleCombo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Vehicle){
                    Vehicle v = (Vehicle) value;
                    String text = v.getLicencePlate();
                    if(v.getvType() != null){
                        text += " - " + v.getvType().getBrand() + " " + v.getvType().getModel();
                    }
                    setText(text);
                }
                return this;
            }
        });

        /* ------- TYPE COMBO ------- */
        this.typeCombo = new JComboBox<>(new String[]{"repair", "maintenance"});
        this.typeCombo.setFont(fieldFont);
        this.typeCombo.setPreferredSize(new Dimension(300, 40));

        /* ------- EMPLOYEE FIELD ------- */
        this.employeeField = new JTextField();
        this.employeeField.setFont(fieldFont);
        this.employeeField.setPreferredSize(new Dimension(450, 40));

        /* ------- DATE SPINNER ------- */
        JSpinner dateSpinnerTemp = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinnerTemp, "dd/MM/yyyy");
        dateSpinnerTemp.setEditor(editor);
        editor.getTextField().setFont(fieldFont);
        dateSpinnerTemp.setPreferredSize(new Dimension(200, 40));
        this.dateSpinner = dateSpinnerTemp;

        /* ------- PRICE FIELD ------- */
        this.priceField = new JTextField("0.0");
        this.priceField.setFont(fieldFont);
        this.priceField.setPreferredSize(new Dimension(200, 40));

        /* ------- COMMENTS AREA ------- */
        this.commentsArea = new JTextArea(12, 50);
        this.commentsArea.setFont(fieldFont);
        this.commentsArea.setLineWrap(true);
        this.commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScroll = new JScrollPane(commentsArea);
        commentsScroll.setPreferredSize(new Dimension(1200, 280));  // Augmenté de 200 à 280px

        /* ------- URGENT CHECKBOX ------- */
        this.urgentCheckBox = new JCheckBox("Mark as urgent");
        this.urgentCheckBox.setFont(new Font("Arial", Font.BOLD, 16));
        this.urgentCheckBox.setForeground(ColorPalette.RED_CRITICAL);
        this.urgentCheckBox.setOpaque(false);

        /* ------- LAYOUT ASSEMBLY ------- */
        //gbc.insets = new Insets(20, 50, 20, 50);
        //gbc.fill = GridBagConstraints.HORIZONTAL;

        /* ------- LAYOUT ASSEMBLY ------- */
        gbc.insets = new Insets(18, 40, 18, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

// Line 0 - Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 20, 20, 20);
        this.add(backButton, gbc);

// Titre centré sur le reste de la ligne
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(titlePane, gbc);

// Reset pour le reste
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(18, 40, 18, 20);

// ------------ Ligne 1 : Vehicle / Type / Employee ------------
        gbc.gridy = 1;

// Vehicle label
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        this.add(vehicleLabel, gbc);

// Vehicle combo (doit s’étirer)
        gbc.gridx = 1;
        gbc.weightx = 0.35;                  
        gbc.insets = new Insets(18, 10, 18, 35);
        this.add(vehicleCombo, gbc);

// Type label
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(18, 40, 18, 10);
        this.add(typeLabel, gbc);

// Type combo
        gbc.gridx = 3;
        gbc.weightx = 0.25;
        gbc.insets = new Insets(18, 10, 18, 35);
        this.add(typeCombo, gbc);

// Employee label
        gbc.gridx = 4;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(18, 40, 18, 10);
        this.add(employeeLabel, gbc);

// Employee field
        gbc.gridx = 5;
        gbc.weightx = 0.40;
        gbc.insets = new Insets(18, 10, 18, 40);
        this.add(employeeField, gbc);

// ------------ Ligne 2 : Date / Price ------------
        gbc.gridy = 2;

// Date label
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(18, 40, 18, 20);
        this.add(dateLabel, gbc);

// Date spinner
        gbc.gridx = 1;
        gbc.weightx = 0.35;
        gbc.insets = new Insets(18, 10, 18, 35);
        this.add(dateSpinner, gbc);

// Price label
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(18, 40, 18, 10);
        this.add(priceLabel, gbc);

// Price field
        gbc.gridx = 3;
        gbc.weightx = 0.25;
        gbc.insets = new Insets(18, 10, 18, 35);
        this.add(priceField, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.0;
        this.add(Box.createHorizontalStrut(1), gbc);

        gbc.gridx = 5;
        gbc.weightx = 0.40;
        this.add(Box.createHorizontalStrut(1), gbc);

// ------------ Ligne 3 : Urgent ------------
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(10, 40, 10, 20);
        this.add(urgentCheckBox, gbc);
        gbc.gridwidth = 1;

// ------------ Ligne 4 : Comments ------------
        gbc.gridy = 4;

// Label à gauche
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(18, 40, 18, 20);
        gbc.fill = GridBagConstraints.NONE;
        this.add(commentsLabel, gbc);

// TextArea commence sous les champs (comme la capture 2)
        gbc.gridx = 1;
        gbc.gridwidth = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(18, 10, 18, 40);
        gbc.fill = GridBagConstraints.BOTH;
        this.add(commentsScroll, gbc);

// Reset
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;

// ------------ Ligne 5 : Save Button ------------
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(25, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.add(saveButton, gbc);

// ------------ Ligne 6 : Success message ------------
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(msgSuccess, gbc);
        msgSuccess.setVisible(false);

        /* ------- SAVE BUTTON ACTION ------- */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate
                Vehicle selectedVehicle = (Vehicle) vehicleCombo.getSelectedItem();
                String selectedTypeStr = (String) typeCombo.getSelectedItem();

                if(selectedVehicle == null){
                    JOptionPane.showMessageDialog(InterventionPanel.this,
                            "Please select a vehicle",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(selectedTypeStr == null){
                    JOptionPane.showMessageDialog(InterventionPanel.this,
                            "Please select an intervention type",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Find InterventionType by matching the enum name
                InterventionType selectedType = null;
                for(InterventionType t : types){
                    if(t.getName().equalsIgnoreCase(selectedTypeStr)){
                        selectedType = t;
                        break;
                    }
                }

                if(selectedType == null){
                    JOptionPane.showMessageDialog(InterventionPanel.this,
                            "Intervention type not found in database",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get values
                String employeeName = employeeField.getText().trim();
                LocalDate date = ((Date) dateSpinner.getValue()).toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                String comments = commentsArea.getText().trim();
                boolean urgent = urgentCheckBox.isSelected();

                // Create intervention
                try{
                    Intervention intervention = new Intervention(selectedVehicle, selectedType, date);

                    // If employee name provided, try to find and link Employee
                    if(!employeeName.isEmpty()){
                        try{
                            List<Employee> allEmployees = server.getEmployees();
                            for(Employee emp : allEmployees){
                                if(emp.getFullName().equalsIgnoreCase(employeeName)){
                                    intervention.setEmployee(emp);
                                    break;
                                }
                            }
                        } catch(Exception ex){
                            // Employee not found, continue without employee
                        }
                    }

                    // Save to DB
                    if(server.addIntervention(intervention)){
                        msgSuccess.setVisible(true);

                        // Clear form
                        if(vehicleCombo.getItemCount() > 0){
                            vehicleCombo.setSelectedIndex(0);
                        }
                        typeCombo.setSelectedIndex(0);
                        employeeField.setText("");
                        dateSpinner.setValue(new Date());
                        commentsArea.setText("");
                        urgentCheckBox.setSelected(false);
                        priceField.setText("0.0");

                        // Hide message after 3 seconds
                        Timer timer = new Timer(3000, evt -> msgSuccess.setVisible(false));
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        JOptionPane.showMessageDialog(InterventionPanel.this,
                                "Failed to create intervention. Please try again.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch(Exception ex){
                    JOptionPane.showMessageDialog(InterventionPanel.this,
                            "Error creating intervention: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        this.setPreferredSize(new Dimension(1920, 1080));
    }

    /* ------- METHODS ------- */
    private void loadData(){
        if(server == null){
            this.vehicles = new java.util.ArrayList<>();
            this.types = new java.util.ArrayList<>();
            return;
        }

        try{
            this.vehicles = server.getVehicles();
            this.types = server.getInterventionTypes();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,
                    "Error loading data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.vehicles = new java.util.ArrayList<>();
            this.types = new java.util.ArrayList<>();
        }
    }
}
