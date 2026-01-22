package gui;

import gui.components.MainPanel;
import gui.panel.*;
import gui.utils.ViewConstant;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

//TODO :
/*
    2- Dynamic Search in search for a Vehicle
    4- Edit Main Frame to see next Intervention
    5- Clear the DataBase (delete some test entries)
    6- Vehicle Plan when selecting a Vehicle
    8- Modify Estimated price -> use function to calculate price
 */

public class MainFrame extends JFrame {
    private CardLayout layoutManager;
    private JPanel supportPanel;
    private QueryRetyre server;

    private MainPanel mainPanel;
    private AddVehicleTypePanel addVehicleTypePanel;
    private AddVehiclePanel addVehiclePanel;
    private SearchVehiclePanel searchPanel;
    private InterventionPanel interventionPanel;
    private HistoryPanel historyPanel;

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
        this.addVehiclePanel = new AddVehiclePanel(this.server,this.supportPanel, this.layoutManager);
        this.searchPanel = new SearchVehiclePanel(this.server, this.supportPanel, this.layoutManager);
        this.interventionPanel = new InterventionPanel(this.server,this.supportPanel,this.layoutManager);
        this.historyPanel = new HistoryPanel(this.server, this.supportPanel,this.layoutManager);


        this.add(supportPanel);
        this.supportPanel.add(mainPanel,ViewConstant.MAIN_VIEW);
        this.supportPanel.add(addVehicleTypePanel,ViewConstant.ADDVT_VIEW);
        this.supportPanel.add(addVehiclePanel, ViewConstant.ADDV_VIEW);
        this.supportPanel.add(searchPanel, ViewConstant.VEHICLELIST_VIEW);
        this.supportPanel.add(interventionPanel, ViewConstant.INTERVENTION_VIEW);
        this.supportPanel.add(historyPanel,ViewConstant.HISTORY_VIEW);

        this.layoutManager.show(supportPanel,ViewConstant.MAIN_VIEW);


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
