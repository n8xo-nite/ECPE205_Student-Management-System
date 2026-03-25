package panels;

import model.DataStore;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    private JLabel aboutLabel;
    public AboutPanel() {

        setLayout(new BorderLayout());
        // ABOUT US
        JLabel title = new JLabel("ABOUT US", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);
//
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        aboutLabel = new JLabel("Members: INOCENCIO , LOPEZ , GONZALES , CASTILLON" );


        aboutLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        aboutLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(aboutLabel);

        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);


    }
}
