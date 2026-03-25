package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Central in-memory data store. All panels read/write through this class.
 * Data is stored in an ArrayList of Student objects (no database).
 * 
 * ASSIGNED TO: Student 1 (Data Model Owner)
 * 
 * TODO for Student 1:
 * - Add methods as other teammates need them (e.g., updateStudent,
 * searchByName)
 * - Keep this class as the SINGLE source of truth for all data
 */
public class DataStore {
  private static final DataStore instance = new DataStore();
  private final List<Student> students;

  private DataStore() {
    students = new ArrayList<>();
    // Pre-load some sample data for testin
    students.add(new Student("1001", "John Doe", 20, "Engineering", "CpE", "2", "09123456789"));
    students.add(new Student("1002", "Jane Smith", 21, "IT", "BSIT", "3", "09987654321"));
    students.add(new Student("1003", "Michael Cruz", 19, "Computer Science", "BSCS", "1", "09112223344"));
  }

  /** Get the singleton instance */
  public static DataStore getInstance() {
    return instance;
  }

  /** Get all students */
  public List<Student> getAllStudents() {
    return students;
  }

  /** Add a new student */
  public void addStudent(Student student) {
    students.add(student);
  }

  /** Remove a student by index */
  public void removeStudent(int index) {
    if (index >= 0 && index < students.size()) {
      students.remove(index);
    }
  }

  /** Find a student by ID */
  public Student findById(String id) {
    for (Student s : students) {
      if (s.getId().equals(id)) {
        return s;
      }
    }
    return null;
  }


  /** Update student via ID */
  public Student updateStudent(String id){

      return null;
  }

  /** Search via name */
  public Student searchByName(String name){

      return null;
  }

  /** Get total number of students */
  public int getCount() {
    return students.size();
  }
}
