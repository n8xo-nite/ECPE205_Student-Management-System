package panels;

import model.DataStore;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel for editing and deleting students.
 *
 * ASSIGNED TO: Student 4 (Edit / Delete Feature Owner)
 * or split between Student 4 & Student 5 if preferred
 *
 * TODO:
 * - Allow selecting a row and editing the student's fields
 * - Add "Update" button to save changes to the DataStore
 * - Add "Delete" button with a confirmation dialog
 * - Add input validation on edit
 * - Refresh the table after any modification
 */
public class EditStudentPanel extends JPanel {
  private DefaultTableModel tableModel;
  private JTable table;
  private JTextField idField, nameField, ageField, fieldField, courseField, yearLevelField, contactField;

  public EditStudentPanel() {
    setLayout(new BorderLayout());

    JLabel title = new JLabel("Edit / Delete Student", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 24));
    title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
    add(title, BorderLayout.NORTH);

    String[] columns = { "Student ID", "Name", "Age" , "Field" , "Course" , "Year Level" , "Contact Number" };
    tableModel = new DefaultTableModel(columns, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);
    table.setRowHeight(25);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

    table.getSelectionModel().addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        populateFields();
      }
    });

    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));

    JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    formPanel.add(new JLabel("ID:"));
    idField = new JTextField(10);
    idField.setEditable(false);
    formPanel.add(idField);

    formPanel.add(new JLabel("Name:"));
    nameField = new JTextField(15);
    formPanel.add(nameField);

    formPanel.add(new JLabel("Age:"));
    ageField = new JTextField(5);
    formPanel.add(ageField);

    formPanel.add(new JLabel("Field:"));
    fieldField = new JTextField(10);
    formPanel.add(fieldField);

    formPanel.add(new JLabel("Course:"));
    courseField = new JTextField(10);
    formPanel.add(courseField);

    formPanel.add(new JLabel("Year Level:"));
    yearLevelField = new JTextField(5);
    formPanel.add(yearLevelField);

    formPanel.add(new JLabel("Contact Number:"));
    contactField = new JTextField(12);
    formPanel.add(contactField);

    bottomPanel.add(formPanel, BorderLayout.CENTER);

    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

    JButton updateBtn = new JButton("Update");
    updateBtn.addActionListener(e -> updateStudent());
    btnPanel.add(updateBtn);

    JButton deleteBtn = new JButton("Delete");
    deleteBtn.addActionListener(e -> deleteStudent());
    btnPanel.add(deleteBtn);

    JButton refreshBtn = new JButton("Refresh");
    refreshBtn.addActionListener(e -> loadData());
    btnPanel.add(refreshBtn);

    bottomPanel.add(btnPanel, BorderLayout.SOUTH);
    add(bottomPanel, BorderLayout.SOUTH);

    loadData();
  }

  private void loadData() {
    tableModel.setRowCount(0);
    List<Student> students = DataStore.getInstance().getAllStudents();
    for (Student s : students) {
      tableModel.addRow(s.toTableRow());
    }
    clearFields();
  }

  private void populateFields() {
    int row = table.getSelectedRow();
    if (row >= 0) {
      idField.setText(tableModel.getValueAt(row, 0).toString());
      nameField.setText(tableModel.getValueAt(row, 1).toString());
      ageField.setText(tableModel.getValueAt(row, 2).toString());
      fieldField.setText(tableModel.getValueAt(row, 3).toString());
      courseField.setText(tableModel.getValueAt(row, 4).toString());
      yearLevelField.setText(tableModel.getValueAt(row, 5).toString());
      contactField.setText(tableModel.getValueAt(row, 6).toString());
    }
  }

  private void updateStudent() {
    int row = table.getSelectedRow();
    if (row < 0) {
      JOptionPane.showMessageDialog(this, "Select a student to update.", "Info", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    String name = nameField.getText().trim();
    String ageText = ageField.getText().trim();
    String field = fieldField.getText().trim();
    String course = courseField.getText().trim();
    String yearLevel = yearLevelField.getText().trim();
    String contact = contactField.getText().trim();

    if (name.isEmpty() || ageText.isEmpty() || field.isEmpty() || course.isEmpty() || yearLevel.isEmpty() || contact.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
      return;
    }

    int age;
    try {
      age = Integer.parseInt(ageText);
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Age must be a valid number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
      return;
    }

    Student student = DataStore.getInstance().getAllStudents().get(row);
    student.setName(name);
    student.setAge(age);
    student.setCourse(course);
    student.setYearLevel(yearLevel);
    student.setContactNumber(contact);

    JOptionPane.showMessageDialog(this, "Student updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
    loadData();
  }

  private void deleteStudent() {
    int row = table.getSelectedRow();
    if (row < 0) {
      JOptionPane.showMessageDialog(this, "Select a student to delete.", "Info", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this student?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
      DataStore.getInstance().removeStudent(row);
      JOptionPane.showMessageDialog(this, "Student deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
      loadData();
    }
  }

  private void clearFields() {
    idField.setText("");
    nameField.setText("");
    ageField.setText("");
    fieldField.setText("");
    courseField.setText("");
    yearLevelField.setText("");
    contactField.setText("");
  }
}
