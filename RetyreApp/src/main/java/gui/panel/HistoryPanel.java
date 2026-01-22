package gui.panel;

import gui.utils.BackButton;
import model.Intervention;
import model.Vehicle;
import querry.QueryRetyre;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryPanel extends JPanel {
    private BackButton backButton;
    private QueryRetyre server;
    private List<Vehicle> vehicleList;
    private JComboBox<Vehicle> vehicleJComboBox;


    private JScrollPane sPanel;
    private String[] jTableColumns ={"Intervention Name", "Intervention Type", "Date", "Price","Status","Valid Until"};
    private List<Intervention> interventions;
    private AbstractTableModel tableModel;

    public HistoryPanel(QueryRetyre server, JPanel support, CardLayout cl){
        super();
        this.backButton = new BackButton(support,cl);
        this.server = server;
        this.vehicleList = server.getVehicles();

        DefaultComboBoxModel<Vehicle> model = new DefaultComboBoxModel<>();
        for (Vehicle v:vehicleList){
            model.addElement(v);
        }
        this.vehicleJComboBox= new JComboBox<>();
        vehicleJComboBox.setModel(model);
        vehicleJComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
               super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
               if (value instanceof Vehicle){
                   Vehicle v=(Vehicle)value;
                   setText(v.getLicencePlate() + " - " + v.getvType().getBrand() + " - " + v.getvType().getModel());
               }
                return this;
            }
        });

        vehicleJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle v = (Vehicle) vehicleJComboBox.getSelectedItem();
                interventions = server.getInterventionsVehicle(v);
                tableModel.fireTableDataChanged();
            }
        });


        tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return interventions == null ? 0:interventions.size();
            }

            @Override
            public int getColumnCount() {
                return jTableColumns.length;
            }

            @Override
            public String getColumnName(int column){
                return jTableColumns[column];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Intervention i = interventions.get(rowIndex);
                switch (columnIndex){
                    case 0 : return i.getInterventionType().getName();
                    case 1 : return i.getInterventionType().getCategory();
                    case 2 : return i.getInterventionDate();
                    case 3 : return i.getPrice();
                    case 4 : return i.getStatus();
                    case 5 : return i.getNextDate();
                    default: return null;
                }
            }
        };

        JTable jTable = new JTable(tableModel);
        this.sPanel = new JScrollPane(jTable);




        this.add(backButton);
        this.add(vehicleJComboBox);
        this.add(sPanel,BorderLayout.CENTER);

    }
}
