package JdbcProj;

public class EmployeeDaoFactory {
    private static EmployeeDao employeeDao;

    private EmployeeDaoFactory(){
    }
    // singleton make one instance of EmployeeDAO
    public static EmployeeDao getEmployeeDao(){
        if(employeeDao == null)
            employeeDao = new EmployeeDaoImpl();
        return employeeDao;
    }
}
