package gui;

import gui.components.Body;
import gui.components.Header;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private QueryRetyre server;
    private Header header;
    private Body body;
    public MainFrame(){
        super();
        try{
            this.server = new QueryRetyre();
        } catch (Exception e) {
            JOptionPane optPane = new JOptionPane();
            JOptionPane.showMessageDialog(optPane,"Error - Impossible to reach SQL Server");
        }

        this.header = new Header();
        this.body = new Body();

        this.setTitle("Retyre - Auto2I FrameWork");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(header,BorderLayout.NORTH);
        this.add(body,BorderLayout.CENTER);

        this.setSize(1920,1080);
        this.setVisible(true);
    }

    static void main() {
        new MainFrame();
    }
}
