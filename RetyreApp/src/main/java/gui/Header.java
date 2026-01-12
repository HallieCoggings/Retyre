package gui;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    private Dimension dimension;
    private GridLayout layout;
    public Header(){
        super();
        this.dimension = new Dimension(300,100);
        this.setBackground(Color.green);
        this.setPreferredSize(this.dimension);
        this.layout = new GridLayout(2,4);
    }
}
