package gui;

import gui.components.MainPanel;
import gui.panel.AddVehicleTypePanel;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout layoutManager;
    private JPanel supportPanel;
    private QueryRetyre server;

    private MainPanel mainPanel;
    private AddVehicleTypePanel addVehicleTypePanel;

    private static final String MAIN_VIEW = "main_Pan";
    private static final String ADDVT_VIEW = "addVT_Pan";

    public MainFrame(){
        super();
        this.layoutManager = new CardLayout();
        this.supportPanel = new JPanel(this.layoutManager);
        try{
            this.server = new QueryRetyre();
        } catch (Exception e) {
            JOptionPane optPane = new JOptionPane();
            JOptionPane.showMessageDialog(optPane,"Error - Impossible to reach SQL Server");
        }
        this.mainPanel = new MainPanel(this.supportPanel, this.layoutManager);
        this.addVehicleTypePanel = new AddVehicleTypePanel(this.server,this.supportPanel, this.layoutManager);


        this.add(supportPanel);
        this.supportPanel.add(mainPanel,MAIN_VIEW);
        this.supportPanel.add(addVehicleTypePanel,ADDVT_VIEW);

        this.layoutManager.show(supportPanel,MAIN_VIEW);


        this.setTitle("Retyre - Auto2i Framework");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    static void main() {
        new MainFrame();
    }
}
