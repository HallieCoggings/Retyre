package gui.panel;

import gui.utils.BackButton;
import model.Vehicle;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private BackButton backButton;
    private QueryRetyre server;
    private List<Vehicle> vehicleList;
    private JComboBox<Vehicle> vehicleJComboBox;

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

        this.add(backButton);
        this.add(vehicleJComboBox);

    }
}
