import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages employee registrations and attendance record tracking.
 */
public class AttendanceManager {
  private List<Employee> employees;
  private List<AttendanceRecord> attendanceRecords;

  /**
   * Initializes a new AttendanceManager with empty employee and attendance lists.
   */
  public AttendanceManager() {
    this.employees = new ArrayList<>();
    this.attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers a new employee into the system.
   *
   * @param employee the employee to register
   * @throws EmployeeAlreadyExistsException if an employee with the same ID
   *                                        already exists
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee emp : employees) {
      if (emp.getEmployeeId().equalsIgnoreCase(employee.getEmployeeId())) {
        throw new EmployeeAlreadyExistsException(
            "Employee with ID " + employee.getEmployeeId() + " is already registered.");
      }
    }
    employees.add(employee);
  }

  /**
   * Finds and returns an employee by their employee ID.
   *
   * @param employeeId unique identifier of the employee to search for
   * @return the matching Employee object
   * @throws EmployeeNotFoundException if no employee exists with the given ID
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee emp : employees) {
      if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
        return emp;
      }
    }
    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
  }

  /**
   * Records a time-in for the specified employee.
   *
   * @param employeeId the ID of the employee clocking in
   * @throws EmployeeNotFoundException  if the employee ID is invalid
   * @throws InvalidAttendanceException if the employee already has an active
   *                                    attendance record
   */
  public void timeIn(String employeeId) throws EmployeeNotFoundException, InvalidAttendanceException {
    // Verify employee existence
    findEmployeeById(employeeId);

    // Check for active clock-in (where timeOut is null)
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equalsIgnoreCase(employeeId) && record.getTimeOut() == null) {
        throw new InvalidAttendanceException(
            "Employee " + employeeId + " already has an active attendance record.");
      }
    }

    // Create new clock-in record
    attendanceRecords.add(new AttendanceRecord(employeeId, LocalDateTime.now()));
  }

  /**
   * Records a time-out for the specified employee.
   *
   * @param employeeId the ID of the employee clocking out
   * @throws EmployeeNotFoundException  if the employee ID is invalid
   * @throws InvalidAttendanceException if no active attendance record is found
   */
  public void timeOut(String employeeId) throws EmployeeNotFoundException, InvalidAttendanceException {
    // Verify employee existence
    findEmployeeById(employeeId);

    // Find active record to update timeOut
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equalsIgnoreCase(employeeId) && record.getTimeOut() == null) {
        record.setTimeOut(LocalDateTime.now());
        return;
      }
    }

    throw new InvalidAttendanceException(
        "Employee " + employeeId + " has no active attendance record to time out from.");
  }

  /**
   * Retrieves all completed attendance records for a given employee using a
   * Lambda expression.
   *
   * @param employeeId the ID of the employee whose completed records are to be
   *                   retrieved
   * @return a list of completed attendance records
   * @throws EmployeeNotFoundException if the employee ID does not exist
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId) throws EmployeeNotFoundException {
    // Verify employee existence
    findEmployeeById(employeeId);

    // Lambda expression filtering for matching ID and non-null timeOut
    return attendanceRecords.stream()
        .filter(rec -> rec.getEmployeeId().equalsIgnoreCase(employeeId) && rec.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}