package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.VehicleType;
import model.enums.EnergyType;
import model.enums.TransmissionType;
import querry.QueryRetyre;

public class AddVehiclePanel extends JPanel {

    private BackButton backButton;
    private LayoutManager layout;
    private JButton saveButton;
    private JTextField brandField;
    private JTextField modelField;
    private JComboBox<EnergyType> energyField;
    private JSpinner nbDoorField;
    private JComboBox<TransmissionType> gearField;
    private JSpinner nbPlaceField;
    private JSpinner powerField;
    private QueryRetyre server;


    public AddVehiclePanel(QueryRetyre server){
        super();
        this.setBackground(Color.decode("#FFd75F"));
        this.backButton = new BackButton();
        this.saveButton = new JButton("Create a new vehicle Type");
        this.server = server;

        this.layout = new GridBagLayout();
        this.setLayout(this.layout);
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("Arial",Font.BOLD,18);
        Font fontField = new Font("Arial",Font.PLAIN,18);

        //Set Label
        JLabel titleLab = new JLabel("Panel to add a new Type of Vehicle");
        JLabel brandLab = new JLabel("Brand :");
        JLabel modelLab = new JLabel("Model :");
        JLabel energyLab = new JLabel("Energy :");
        JLabel nbDoorLab =new JLabel("Number of Doors :");
        JLabel gearLab =new JLabel("Gear :");
        JLabel nbPlaceLab = new JLabel("Number of Place :");
        JLabel powerLab = new JLabel("Power :");
        JLabel compoLab = new JLabel("Components :");

        titleLab.setFont(font);brandLab.setFont(font);modelLab.setFont(font);energyLab.setFont(font);
        nbDoorLab.setFont(font);gearLab.setFont(font);nbPlaceLab.setFont(font);powerLab.setFont(font);
        compoLab.setFont(font);

        //Set JtextFile
        this.brandField = new JTextField(20);
        this.modelField = new JTextField(20);
        this.energyField = new JComboBox<>(EnergyType.values());
        this.nbDoorField = new JSpinner(new SpinnerNumberModel(5,3,9,2));
        this.gearField = new JComboBox<>(TransmissionType.values());
        this.nbPlaceField = new JSpinner(new SpinnerNumberModel(5,2,8,3));
        this.powerField = new JSpinner(new SpinnerNumberModel(100,1,9999,1));
        this.brandField.setFont(fontField);
        this.modelField.setFont(fontField);
        this.energyField.setFont(fontField);
        this.nbDoorField.setFont(fontField);
        this.gearField.setFont(fontField);
        this.nbPlaceField.setFont(fontField);
        this.powerField.setFont(fontField);



        // -- Set insets
        gbc.insets = new Insets(50,50,50,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Line 0
        gbc.gridx = 0; //Col 0
        gbc.gridy = 0; //Row 0
        gbc.gridwidth = 1; // element takes one column
        this.add(backButton,gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(50,250,50,50);
        this.add(titleLab,gbc);

        gbc.insets = new Insets(50,50,50,50);

        gbc.gridwidth = 1;

        //Line 1
        gbc.gridy = 1;
        gbc.gridx = 0; this.add(brandLab,gbc);
        gbc.gridx = 1; this.add(this.brandField,gbc);
        gbc.gridx = 2; this.add(modelLab,gbc);
        gbc.gridx = 3; this.add(this.modelField,gbc);

        //Line 2
        gbc.gridy = 2;
        gbc.gridx = 0; this.add(energyLab,gbc);
        gbc.gridx = 1; this.add(this.energyField,gbc);
        gbc.gridx = 2; this.add(gearLab,gbc);
        gbc.gridx = 3; this.add(this.gearField,gbc);

        //Line 3
        gbc.gridy = 3;
        gbc.gridx = 0; this.add(nbDoorLab,gbc);
        gbc.gridx = 1; this.add(this.nbDoorField,gbc);
        gbc.gridx = 2; this.add(nbPlaceLab,gbc);
        gbc.gridx = 3; this.add(this.nbPlaceField,gbc);

        //Line 4
        gbc.gridy = 4;
        gbc.gridx = 0; this.add(powerLab,gbc);
        gbc.gridx = 1; this.add(this.powerField,gbc);
        gbc.gridx = 2; this.add(compoLab,gbc);
        gbc.gridx = 3; this.add(new JTextField(20),gbc);

        //Line 5
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 4;

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brand = brandField.getText();
                String model = modelField.getText();
                EnergyType energy = (EnergyType) energyField.getSelectedItem();
                TransmissionType gear = (TransmissionType) gearField.getSelectedItem();
                int nbDoor = (int) nbDoorField.getValue();
                int nbPlace = (int) nbPlaceField.getValue();
                int power = (int) powerField.getValue();
                VehicleType vType = new VehicleType(brand,model,energy,gear,nbDoor,nbPlace,power);
                if (server.addVehicle(vType)) {
                    System.out.println("OUI");
                }
            }
        });

        this.add(saveButton,gbc);

        this.setPreferredSize(new Dimension(1920,1080));

    }
    static void main() {
        QueryRetyre serv = new QueryRetyre();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Vehicule Frame Test");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            AddVehiclePanel aVP = new AddVehiclePanel(serv);
            frame.add(aVP);
            frame.setSize(new Dimension(1920, 1080));

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
