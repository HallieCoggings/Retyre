package gui;

import querry.QueryRetyre;

import javax.swing.*;

public class MainFrame extends JFrame {
    private QueryRetyre server;
    public MainFrame(){
        super();
        try{
            this.server = new QueryRetyre();
        } catch (Exception e) {
            JOptionPane optPane = new JOptionPane();
            JOptionPane.showMessageDialog(optPane,"Error - Impossible to reach SQL Server");
        }
        this.setTitle("Retyre - Auto2I FrameWork");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200,800);
        this.setVisible(true);
    }

    static void main() {
        new MainFrame();
    }
}
