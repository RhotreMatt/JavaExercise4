/**
 * Exception thrown when an attendance operation violates business rules.
 */
public class InvalidAttendanceException extends Exception {

  /**
   * Constructs a new InvalidAttendanceException with the specified message.
   *
   * @param message the detail message describing the error
   */
  public InvalidAttendanceException(String message) {
    super(message);
  }
}