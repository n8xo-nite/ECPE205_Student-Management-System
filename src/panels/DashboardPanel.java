package panels;

import model.DataStore;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dashboard panel showing summary/statistics.
 *
 * ASSIGNED TO: Student 2 (Main Frame / Dashboard Owner)
 *
 * TODO for Student 2:
 * - Display total number of students
 * - Add a welcome message or app logo
 * - Show summary statistics (e.g., average age, total count)
 * - Add a refresh button to update the stats
 * - Make it visually appealing (use colors, larger fonts, icons)
 */
public class DashboardPanel extends JPanel {
  private JLabel countLabel;
  private JLabel averageStudent;


  public DashboardPanel() {
    setLayout(new BorderLayout());
    setBackground(new Color(245,245,245));
      Color newGreen = new Color(87, 242, 135);
    // Title

      JPanel banner = new JPanel(new BorderLayout());
      banner.setBackground(newGreen);
      banner.setPreferredSize(new Dimension(0, 100));
      banner.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

      JLabel title = new JLabel("DASHBOARD" , SwingConstants.CENTER);
      title.setFont(new Font("SansSerif", Font.BOLD, 28));
      title.setForeground(new Color(30, 30, 30));
      banner.add(title, BorderLayout.CENTER);
      add(banner,BorderLayout.NORTH);

      // Center content
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    countLabel = new JLabel("Total Students: " + DataStore.getInstance().getCount());
    countLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(countLabel);

    averageStudent = new JLabel("Average Age: " + DataStore.getInstance().getCount());
    averageStudent.setFont(new Font("Arial" , Font.PLAIN , 18));
    averageStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
    centerPanel.add(averageStudent);

    centerPanel.add(Box.createVerticalStrut(20));

    JButton refreshBtn = new JButton("Refresh");

    refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    refreshBtn.addActionListener(e -> refreshData());
    centerPanel.add(refreshBtn);

    add(centerPanel, BorderLayout.CENTER);
  }

  private void refreshData() {

      int cnt = DataStore.getInstance().getCount();
    countLabel.setText("Total Students: " + cnt);

      List<Student> allStudents = DataStore.getInstance().getAllStudents();


      int ageT = 0;
      int avAge =0;

      if (cnt > 0) {
          for (Student s : allStudents) {
              ageT += s.getAge();
          }
          avAge = ageT / DataStore.getInstance().getCount();
          averageStudent.setText("Average Age: " + avAge );

      }else {
          averageStudent.setText("Average Age: 0");
      }



  }

}
