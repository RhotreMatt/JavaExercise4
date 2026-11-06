package exceptions;
/**
 * Exception thrown when attempting to register an employee
 * whose ID already exists in the system.
 */
public class EmployeeAlreadyExistsException extends Exception {

  /**
   * Constructs a new EmployeeAlreadyExistsException with the specified message.
   *
   * @param message the detail message describing the error
   */
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }
}