package gui.components;

import gui.utils.ColorPalette;
import querry.QueryRetyre;

import javax.swing.*;
import java.awt.*;

public class Body extends JPanel {
    private LayoutManager layout;
    private JLabel titleLabel;
    private JPanel statsPanel;

    public Body(){
        super();
        this.layout = new BorderLayout(20,20);
        this.setLayout(this.layout);
        this.setBackground(ColorPalette.BACKGROUND_LIGHT);

        /* ------- TITLE ------- */
        this.titleLabel = new JLabel("Dashboard - Retyre Garage Management", SwingConstants.CENTER);
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        this.titleLabel.setForeground(ColorPalette.DARK_BLUE);
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(30,0,20,0));

        /* ------- STATS PANEL ------- */
        this.statsPanel = new JPanel(new GridLayout(2,2,20,20));
        this.statsPanel.setBackground(ColorPalette.BACKGROUND_LIGHT);
        this.statsPanel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        // Create stat cards
        JPanel vehiclesCard = createStatCard("Vehicles", "0", ColorPalette.DARK_BLUE);
        JPanel interventionsCard = createStatCard("Interventions", "0", ColorPalette.GREEN_OK);
        JPanel urgentCard = createStatCard("Urgent", "0", ColorPalette.RED_CRITICAL);
        JPanel employeesCard = createStatCard("Employees", "0", ColorPalette.ORANGE_URGENT);

        this.statsPanel.add(vehiclesCard);
        this.statsPanel.add(interventionsCard);
        this.statsPanel.add(urgentCard);
        this.statsPanel.add(employeesCard);

        /* ------- ASSEMBLY ------- */
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(statsPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /* ------- HELPER METHODS ------- */
    private JPanel createStatCard(String title, String value, Color color){
        JPanel card = new JPanel(new BorderLayout(10,10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 3),
                BorderFactory.createEmptyBorder(30,30,30,30)
        ));

        JLabel titleLab = new JLabel(title, SwingConstants.CENTER);
        titleLab.setFont(new Font("Arial", Font.BOLD, 20));
        titleLab.setForeground(color);

        JLabel valueLab = new JLabel(value, SwingConstants.CENTER);
        valueLab.setFont(new Font("Arial", Font.BOLD, 48));
        valueLab.setForeground(ColorPalette.BLACK_TEXT);

        card.add(titleLab, BorderLayout.NORTH);
        card.add(valueLab, BorderLayout.CENTER);

        return card;
    }

    public void updateStats(QueryRetyre server){
        if(server != null){
            try{
                int vehicleCount = server.getVehicles().size();
                int interventionCount = server.getInterventions().size();
                int employeeCount = server.getEmployees().size();

                //Update the stat cards
                Component[] cards = statsPanel.getComponents();
                if(cards.length >= 4){
                    updateCard(cards[0], String.valueOf(vehicleCount));
                    updateCard(cards[1], String.valueOf(interventionCount));
                    updateCard(cards[3], String.valueOf(employeeCount));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void updateCard(Component card, String newValue){
        if(card instanceof JPanel){
            JPanel panel = (JPanel) card;
            Component[] components = panel.getComponents();
            for(Component c : components){
                if(c instanceof JLabel){
                    JLabel label = (JLabel) c;
                    if(label.getFont().getSize() == 48){
                        label.setText(newValue);
                        break;
                    }
                }
            }
        }
    }
}
