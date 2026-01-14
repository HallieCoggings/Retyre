package gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    public BackButton(JPanel support, CardLayout layout){
        super("Back To HomePage");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(support,"main_Pan");
            }
        });
    }
}
