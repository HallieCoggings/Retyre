package gui.panel;

import gui.utils.BackButton;
import gui.utils.ColorPalette;
import model.Owner;
import model.Vehicle;
import model.VehicleType;
import querry.QueryRetyre;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AddVehiclePanel extends JPanel {
    private BackButton backButton;
    private LayoutManager layout;
    private JButton saveButton;
    private QueryRetyre server;
    private List<VehicleType> typeV;
    private List<Owner> owners;

    //Field for form
    private JList<VehicleType> vehicleTypeJList;
    private JScrollPane typeVPane;
    private JSpinner mileage;
    private JFormattedTextField licencePlate;
    private JSpinner dateCirculation;
    private JTextField name;
    private JTextField fName;
    private JFormattedTextField personalDetails;
    private JList<Owner> ownerJList;
    private JScrollPane ownersPanel;

    public AddVehiclePanel(QueryRetyre server,JPanel support, CardLayout layout){
        super();
        this.server = server;
        this.backButton = new BackButton(support,layout);
        this.saveButton = new JButton("Add this vehicle");
        JLabel titlePane = new JLabel("Panel to add a vehicle and its owner");
        this.setBackground(ColorPalette.BACKGROUND_LIGHT);

        //Get Vehicle Type
        this.typeV = server.getVehicleType();
        DefaultListModel<VehicleType> model = new DefaultListModel<>();
        for(VehicleType t:typeV){
            model.addElement(t);;
        }

        //Get Owners List
        this.owners = server.getOwners();
        DefaultListModel<Owner> ownerModel = new DefaultListModel<>();
        for (Owner o:owners){
            ownerModel.addElement(o);
        }

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
        JLabel msgSucces = new JLabel("Vehicle and Owner successfully added to DB");
        JLabel ownerLab = new JLabel("Owners already registered :");

        Font font = new Font("Arial",Font.BOLD,18);
        Font fontField = new Font("Arial",Font.PLAIN,18);
        titlePane.setFont(new Font("Arial", Font.BOLD, 28));
        titlePane.setForeground(ColorPalette.DARK_BLUE);
        typeLab.setFont(font);mileageLab.setFont(font);lpLab.setFont(font);dateLab.setFont(font);
        nameLab.setFont(font);fNameLab.setFont(font);perDetLab.setFont(font);msgSucces.setFont(font);
        ownerLab.setFont(font);

        //Set Field
        this.name = new JTextField(20);
        this.fName = new JTextField(20);
        this.mileage = new JSpinner(new SpinnerNumberModel(1500,0,999999,1));

        //Date Editor
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner,"dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        editor.getTextField().setFont(fontField);
        this.dateCirculation = dateSpinner;

        // Formated Text field
        try {
            MaskFormatter plateMask = new MaskFormatter("UU-###-UU");
            plateMask.setPlaceholderCharacter('_');
            this.licencePlate = new JFormattedTextField(plateMask);

            MaskFormatter persoDetailMask = new MaskFormatter("## ## ## ## ##");
            persoDetailMask.setPlaceholderCharacter('_');

            this.personalDetails = new JFormattedTextField(persoDetailMask);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        //Set VehicleType Form
        this.vehicleTypeJList = new JList<>(model);
        this.vehicleTypeJList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus){
             super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
             if (value instanceof VehicleType){
                 VehicleType v = (VehicleType) value;
                 setText(v.getBrand() + " - " + v.getModel());
             }
             return this;
            }
        });
        this.typeVPane = new JScrollPane(this.vehicleTypeJList);
        this.typeVPane.setPreferredSize(new Dimension(200,150));

        //Set Owner Form
        this.ownerJList = new JList<>(ownerModel);
        this.ownerJList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
                if (value instanceof Owner){
                    Owner o = (Owner) value;
                    setText(o.getFullName());
                }
                return this;
            }
        });
        this.ownersPanel = new JScrollPane(this.ownerJList);
        this.ownersPanel.setPreferredSize(new Dimension(200,100));

        this.ownerJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && ownerJList.getSelectedValue()!=null){
                    clearFormField();
                }
            }
        });

        //Set font
        this.vehicleTypeJList.setFont(fontField);this.mileage.setFont(fontField);this.licencePlate.setFont(fontField);
        this.name.setFont(fontField);this.fName.setFont(fontField);
        this.personalDetails.setFont(fontField);this.ownerJList.setFont(fontField);


        //Add a document Listener
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleDocChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleDocChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleDocChange();
            }

            private void handleDocChange(){
                if (name.hasFocus() || fName.hasFocus() ||personalDetails.hasFocus()){
                    clearOwnerList();
                }
            }
        };

        this.name.getDocument().addDocumentListener(dl);
        this.fName.getDocument().addDocumentListener(dl);
        this.personalDetails.getDocument().addDocumentListener(dl);


        //-- Place element
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
        gbc.gridx = 0; this.add(ownerLab,gbc);
        gbc.gridx = 1; this.add(this.ownersPanel,gbc);
        gbc.gridx = 2; this.add(nameLab,gbc);
        gbc.gridx = 3; this.add(this.name,gbc);
        gbc.gridx = 4; this.add(fNameLab,gbc);
        gbc.gridx = 5; this.add(this.fName,gbc);
        gbc.gridx = 6; this.add(perDetLab,gbc);
        gbc.gridx = 7; this.add(this.personalDetails,gbc);

        // - Line 2
        gbc.gridy = 2;
        gbc.gridx = 0; this.add(typeLab,gbc);
        gbc.gridx = 1; this.add(this.typeVPane,gbc);
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
        gbc.gridwidth = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        saveButton.setPreferredSize(new Dimension(500,70));
        this.add(saveButton,gbc);

        //Line 5
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 7;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.add(msgSucces,gbc);
        msgSucces.setVisible(false);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Owner owner;
                if (ownerJList.getSelectedIndex()!=-1){
                    owner = ownerJList.getSelectedValue();
                }else{
                    String ownerName = name.getText();
                    String ownerFName = fName.getText();
                    String ownerPersonal = personalDetails.getText();
                    owner = new Owner(ownerName,ownerFName,ownerPersonal);
                }
                VehicleType vehicleType = vehicleTypeJList.getSelectedValue();
                String vehicleLP = licencePlate.getText();
                int vehicleKm = (int) mileage.getValue();
                LocalDate vehicleDate = ((Date) dateCirculation.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // JSpinner uses Date which is deprecated so we convert it

                Vehicle vehicle = new Vehicle(vehicleLP,vehicleType,owner,vehicleKm,vehicleDate);
                if (server.addVehicle(vehicle,owner,vehicleType)){
                    msgSucces.setVisible(true);
                }

            }
        });

        this.setPreferredSize(new Dimension(1920,1080));
    }

    public void clearFormField(){
        this.name.setText("");
        this.fName.setText("");
        this.personalDetails.setText("");
    }
    private void clearOwnerList () {
        if (ownerJList.getSelectedIndex() != -1){
            ownerJList.clearSelection();;
        }
    }
}
