package gui.panel;

import gui.utils.BackButton;
import gui.utils.ColorPalette;
import model.Vehicle;
import model.VehicleType;
import querry.QueryRetyre;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchVehiclePanel extends JPanel {
    private BackButton backButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private QueryRetyre server;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchCriteriaCombo;
    private JLabel resultCountLabel;

    public SearchVehiclePanel(QueryRetyre server, JPanel support, CardLayout layout){
        super();
        this.server = server;
        this.backButton = new BackButton(support, layout);

        /* ------- LAYOUT ------- */
        this.setLayout(new BorderLayout(15,15));
        this.setBackground(ColorPalette.BACKGROUND_LIGHT);

        /* ------- TITLE PANEL ------- */
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));

        JLabel titleLabel = new JLabel("Search Vehicle");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(ColorPalette.DARK_BLUE);

        titlePanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        /* ------- SEARCH PANEL ------- */
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(ColorPalette.BACKGROUND_LIGHT);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ColorPalette.DARK_BLUE, 2),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        JLabel searchLabel = new JLabel("Search by:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));

        this.searchCriteriaCombo = new JComboBox<>(new String[]{
                "License Plate",
                "Brand",
                "Model",
                "Owner Name"
        });
        this.searchCriteriaCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        this.searchCriteriaCombo.setPreferredSize(new Dimension(150, 30));

        this.searchField = new JTextField(25);
        this.searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.searchField.setPreferredSize(new Dimension(250, 30));
        this.searchField.addActionListener(e -> performSearch());

        this.searchButton = new JButton("Search");
        this.searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.searchButton.setBackground(ColorPalette.GREEN_BUTTON);
        this.searchButton.setPreferredSize(new Dimension(100, 30));
        this.searchButton.addActionListener(e -> performSearch());

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(ColorPalette.LIGHT_GREY);
        clearButton.setPreferredSize(new Dimension(100, 30));
        clearButton.addActionListener(e -> clearSearch());

        searchPanel.add(searchLabel);
        searchPanel.add(searchCriteriaCombo);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        /* ------- RESULT COUNT LABEL ------- */
        this.resultCountLabel = new JLabel("Enter search criteria and click Search");
        this.resultCountLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        this.resultCountLabel.setForeground(ColorPalette.GRAY_TEXT);
        this.resultCountLabel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        /* ------- TABLE ------- */
        String[] columns = {"License Plate", "Brand", "Model", "Owner", "Mileage (km)"};
        this.tableModel = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        this.resultTable = new JTable(tableModel);
        this.resultTable.setFont(new Font("Arial", Font.PLAIN, 13));
        this.resultTable.setRowHeight(30);
        this.resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.resultTable.setGridColor(ColorPalette.LIGHT_GREY);

        //Header style
        JTableHeader header = resultTable.getTableHeader();
        header.setBackground(ColorPalette.TABLE_HEADER_BG);
        header.setForeground(ColorPalette.TABLE_HEADER_F);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(ColorPalette.LIGHT_GREY, 2));

        /* ------- ASSEMLBY ------- */
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(resultCountLabel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /* ------- METHODS ------- */
    private void performSearch(){
        if(server == null){return;}

        String searchTerm = searchField.getText().trim();
        if(searchTerm.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter a search term",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);

        try{
            List<Vehicle> allVehicles = server.getVehicles();
            List<Vehicle> filteredVehicles = new ArrayList<>();

            String criteria = (String) searchCriteriaCombo.getSelectedItem();
            String searchLower = searchTerm.toLowerCase();

            for(Vehicle v : allVehicles){
                boolean matches = false;

                switch(criteria){
                    case "License Plate":
                        if(v.getLicencePlate() != null &&
                                v.getLicencePlate().toLowerCase().contains(searchLower)){
                            matches = true;
                        }
                        break;

                    case "Brand":
                        if(v.getvType() != null && v.getvType().getBrand() != null &&
                                v.getvType().getBrand().toLowerCase().contains(searchLower)){
                            matches = true;
                        }
                        break;

                    case "Model":
                        if(v.getvType() != null && v.getvType().getModel() != null &&
                                v.getvType().getModel().toLowerCase().contains(searchLower)){
                            matches = true;
                        }
                        break;

                    case "Owner Name":
                        if(v.getOwner() != null && v.getOwner().getName() != null &&
                                v.getOwner().getName().toLowerCase().contains(searchLower)){
                            matches = true;
                        }
                        if(v.getOwner() != null && v.getOwner().getFirstName() != null &&
                                v.getOwner().getFirstName().toLowerCase().contains(searchLower)){
                            matches = true;
                        }
                        break;
                }

                if(matches){
                    filteredVehicles.add(v);
                }
            }

            //Display results
            for(Vehicle v : filteredVehicles){
                String plate = v.getLicencePlate() != null ? v.getLicencePlate() : "N/A";
                String brand = "N/A";
                String model = "N/A";

                VehicleType vt = v.getvType();
                if(vt != null){
                    brand = vt.getBrand() != null ? vt.getBrand() : "N/A";
                    model = vt.getModel() != null ? vt.getModel() : "N/A";
                }

                String owner = v.getOwner() != null ? v.getOwner().getFullName() : "N/A";
                String mileage = String.format("%.0f", v.getMileage());

                Object[] row = {plate, brand, model, owner, mileage};
                tableModel.addRow(row);
            }

            //Update result count
            int count = filteredVehicles.size();
            if(count == 0){
                resultCountLabel.setText("No vehicles found matching \"" + searchTerm + "\"");
                resultCountLabel.setForeground(ColorPalette.RED_CRITICAL);
            }else if(count == 1){
                resultCountLabel.setText("Found 1 vehicle");
                resultCountLabel.setForeground(ColorPalette.GREEN_OK);
            }else{
                resultCountLabel.setText("Found " + count + " vehicles");
                resultCountLabel.setForeground(ColorPalette.GREEN_OK);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
                    "Error searching vehicles: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            resultCountLabel.setText("Error occurred during search");
            resultCountLabel.setForeground(ColorPalette.RED_CRITICAL);
        }
    }

    private void clearSearch(){
        searchField.setText("");
        tableModel.setRowCount(0);
        resultCountLabel.setText("Enter search criteria and click Search");
        resultCountLabel.setForeground(ColorPalette.GRAY_TEXT);
    }
}
