package gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundButton extends JButton {

    private Color baseColor;
    private Color hoverColor;
    private Color currentColor;
    private int cornerRadius;

    public RoundButton(String text) {
        this(text, ColorPalette.DARK_BLUE, 15);
    }

    public RoundButton(String text, Color backgroundColor) {
        this(text, backgroundColor, 15);
    }

    public RoundButton(String text, Color backgroundColor, int cornerRadius) {
        super(text);
        this.baseColor = backgroundColor;
        this.currentColor = backgroundColor;
        this.cornerRadius = cornerRadius;
        this.hoverColor = backgroundColor.darker();

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentColor = hoverColor;
                repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentColor = baseColor;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws button
        g2.setColor(currentColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        //Fill with text
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }

    public void setBackgroundColor(Color color) {
        this.baseColor = color;
        this.currentColor = color;
        this.hoverColor = color.darker();
        repaint();
    }

    public Color getBackgroundColor() {
        return baseColor;
    }
}
