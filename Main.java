import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Main application entry point to demonstrate system requirements and test
 * cases.
 */
public class Main {

  /**
   * Executes test operations sequentially matching test case specifications.
   *
   * @param args command line arguments (unused)
   */
  public static void main(String[] args) {
    AttendanceManager manager = new AttendanceManager();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    System.out.println("=== EMPLOYEE ATTENDANCE MANAGEMENT SYSTEM TEST SUITE ===\n");

    // --- Test Case 1: Register employees ---
    System.out.println("--- Test Case 1: Register employees ---");
    try {
      manager.registerEmployee(new Employee("E001", "Riri Williams", "IT"));
      System.out.println("The employee is successfully registered.");

      manager.registerEmployee(new Employee("E002", "Nick Fury", "HR"));
      System.out.println("The employee is successfully registered.");
    } catch (EmployeeAlreadyExistsException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // --- Test Case 2: Prevent duplicate employee registration ---
    System.out.println("\n--- Test Case 2: Prevent duplicate employee registration ---");
    try {
      manager.registerEmployee(new Employee("E001", "Thor Son of Odin", "Electrical"));
    } catch (EmployeeAlreadyExistsException e) {
      System.out.println("An EmployeeAlreadyExistsException is thrown and handled: " + e.getMessage());
    }

    // --- Test Case 3: Find an existing employee ---
    System.out.println("\n--- Test Case 3: Find an existing employee ---");
    try {
      Employee emp = manager.findEmployeeById("E001");
      System.out.println("The employee details for " + emp.getName() + " are returned and displayed:");
      System.out.println("   -> " + emp);
    } catch (EmployeeNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // --- Test Case 4: Handle an unregistered employee ---
    System.out.println("\n--- Test Case 4: Handle an unregistered employee ---");
    try {
      manager.findEmployeeById("E999");
    } catch (EmployeeNotFoundException e) {
      System.out.println("An EmployeeNotFoundException is thrown and handled: " + e.getMessage());
    }

    // --- Test Case 5: Record an employee's time-in ---
    System.out.println("\n--- Test Case 5: Record an employee's time-in ---");
    try {
      String empId = "E001";
      manager.timeIn(empId);
      Employee emp = manager.findEmployeeById(empId);
      System.out.println("A new attendance record is created for " + emp.getName() + " (" + empId
          + ") with the current date and time as timeIn. timeOut is null.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // --- Test Case 6: Prevent multiple active attendance records ---
    System.out.println("\n--- Test Case 6: Prevent multiple active attendance records ---");
    try {
      manager.timeIn("E001");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out
          .println("InvalidAttendanceException is thrown and handled. No additional attendance record is created.");
      System.out.println("   Reason: " + e.getMessage());
    }

    // --- Test Case 7: Prevent time-out without time-in / active record ---
    System.out.println("\n--- Test Case 7: Prevent time-out without active record ---");
    try {
      manager.timeOut("E002");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println("InvalidAttendanceException is thrown and handled.");
      System.out.println("   Reason: " + e.getMessage());
    }

    // --- Test Case 8: Record an employee's time-out ---
    System.out.println("\n--- Test Case 8: Record an employee's time-out ---");
    try {
      String empId = "E001";
      manager.timeOut(empId);
      Employee emp = manager.findEmployeeById(empId);
      System.out.println("The existing active attendance record for " + emp.getName() + " (" + empId
          + ") is updated with the current date and time as timeOut.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // --- Test Case 9: Allow a new attendance record after completing a previous
    // one ---
    System.out.println("\n--- Test Case 9: Allow a new attendance record after completing a previous one ---");
    try {
      String empId = "E001";
      manager.timeIn(empId);
      Employee emp = manager.findEmployeeById(empId);
      System.out.println("A new attendance record is created for " + emp.getName()
          + ". The previous completed attendance record remains unchanged.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // --- Test Case 10: Filter completed attendance records using a lambda
    // expression ---
    System.out.println("\n--- Test Case 10: Filter completed attendance records using a lambda expression ---");
    try {
      String empId = "E001";
      Employee emp = manager.findEmployeeById(empId);
      List<AttendanceRecord> completedRecords = manager.getCompletedAttendanceRecords(empId);

      System.out.println("All completed attendance records belonging to " + emp.getName()
          + " (" + empId + ") are displayed. Active attendance records are excluded:");
      for (AttendanceRecord record : completedRecords) {
        String formattedIn = record.getTimeIn().format(formatter);
        String formattedOut = record.getTimeOut().format(formatter);
        System.out.println("   -> [Name: " + emp.getName()
            + " | ID: " + record.getEmployeeId()
            + " | Time-In: " + formattedIn
            + " | Time-Out: " + formattedOut + "]");
      }
    } catch (EmployeeNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}