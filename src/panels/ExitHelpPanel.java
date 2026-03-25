package panels;

import model.DataStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitHelpPanel extends JPanel {
    private JLabel helpLabel;


    public ExitHelpPanel(){

        setLayout(new BorderLayout());
        // ABOUT US
        JLabel title = new JLabel("HELP", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);
//
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        helpLabel = new JLabel("Add Student Via AddStudentTab"  );
        helpLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        helpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(helpLabel);

        JButton exitButton = new JButton("EXIT");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        centerPanel.add(exitButton);

        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

    }



}
