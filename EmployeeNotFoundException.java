/**
 * Exception thrown when an employee ID cannot be found in the system.
 */
public class EmployeeNotFoundException extends Exception {

  /**
   * Constructs a new EmployeeNotFoundException with the specified message.
   *
   * @param message the detail message describing the error
   */
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}