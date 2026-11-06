/**
 * Represents an employee in the system.
 */
public class Employee {
  private String employeeId;
  private String name;
  private String department;

  /**
   * Constructs a new Employee instance.
   *
   * @param employeeId unique identifier for the employee
   * @param name       full name of the employee
   * @param department department where the employee works
   */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
  }

  /**
   * Gets the employee ID.
   *
   * @return the employee ID
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Gets the employee's name.
   *
   * @return the employee's name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the employee's department.
   *
   * @return the department name
   */
  public String getDepartment() {
    return department;
  }

  /**
   * Returns a string representation of the employee, including their ID,
   * name, and department.
   *
   * @return a formatted string containing employee details
   */
  @Override
  public String toString() {
    return "ID: " + employeeId + ", Name: " + name + ", Department: " + department;
  }
}