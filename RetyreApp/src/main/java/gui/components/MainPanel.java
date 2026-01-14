package gui.components;

import gui.MainFrame;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Header header;
    private Body body;
    private LayoutManager layoutManager;

    public MainPanel(JPanel support, CardLayout layout){
        super();
        this.header = new Header(support,layout);
        this.body = new Body();
        this.layoutManager = new BorderLayout();

        this.setLayout(this.layoutManager);

        this.add(header,BorderLayout.NORTH);
        this.add(body,BorderLayout.CENTER);

        this.setSize(1920,1080);
        this.setVisible(true);
    }

}
