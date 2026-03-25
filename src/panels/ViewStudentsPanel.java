package panels;

import model.DataStore;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * Panel for viewing all students in a table.
 *
 * ASSIGNED TO: Student 4 (View / List Feature Owner)
 *
 * TODO for Student 4:
 * - Add more columns to match all Student fields
 * - Add a refresh button to reload data
 * - Add sorting functionality (click column headers)
 * - Improve table styling (row colors, column widths)
 * - Show "No records found" when the list is empty
 * - Optionally add pagination if the list is long
 */
public class ViewStudentsPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;

    public ViewStudentsPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("All Students", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        String[] columns = { "Student ID", "Name", "Age" , "Email", "Course" ,"Year Level" ,"Contact Number" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(240, 240, 240));
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(new Color(184, 207, 229));
                }

                return c;
            }
        });

        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(184, 207, 229));

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        JButton refreshBtn = new JButton("Refresh List");
        refreshBtn.addActionListener(e -> loadData());
        buttonPanel.add(refreshBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Student> students = DataStore.getInstance().getAllStudents();
        for (Student s : students) {
            tableModel.addRow(s.toTableRow());
        }
    }
}
