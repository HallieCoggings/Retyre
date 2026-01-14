package gui.components;

import javax.swing.*;
import java.awt.*;

public class Body extends JPanel {
    private BorderLayout layout;
    private JScrollPane futureIntervention;
    private JPanel calendar;

    public Body(){
        super();
        this.setBackground(Color.pink);
        this.layout = new BorderLayout();
        this.futureIntervention = new JScrollPane();
        this.calendar = new JPanel();

        this.futureIntervention.setBackground(Color.blue);
        this.futureIntervention.setPreferredSize(new Dimension(1000,700));
        this.setLayout(this.layout);
        this.calendar.setBackground(Color.blue);
        this.calendar.setPreferredSize(new Dimension(200,700));

        this.add(futureIntervention,BorderLayout.WEST);
        this.add(calendar,BorderLayout.EAST);
    }
}
