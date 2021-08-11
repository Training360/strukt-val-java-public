package employees;

public interface EmployeeRepository {

    Employee findEmployeeLikeName(String name);
}
