import java.time.LocalDateTime;

/**
 * Represents an employee's attendance record (clock-in and clock-out
 * timestamps).
 */
public class AttendanceRecord {
  private String employeeId;
  private LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Constructs a new AttendanceRecord starting from clock-in.
   *
   * @param employeeId unique identifier for the employee
   * @param timeIn     date and time when the employee clocked in
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  /**
   * Gets the employee ID associated with this record.
   *
   * @return the employee ID
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Gets the time-in timestamp.
   *
   * @return time-in LocalDateTime
   */
  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  /**
   * Gets the time-out timestamp.
   *
   * @return time-out LocalDateTime, or null if currently active
   */
  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  /**
   * Sets the time-out timestamp.
   *
   * @param timeOut date and time when the employee clocked out
   */
  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }
}