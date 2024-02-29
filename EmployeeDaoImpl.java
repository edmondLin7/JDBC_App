package JdbcProj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
    Implementation of all EmployDao methods
    insert, delete, etc.
 */
public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee saved");
        } else {
            System.out.println("Opps! something went wrong, please try again");
        }
    }

    @Override
    public void updateEmployee(int id, String name, String email) throws SQLException {
        String sql = "UPDATE employee SET name = ?, email = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee updated");
        } else {
            System.out.println("Opps! something went wrong, please try again");
        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE from employee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee deleted");
        } else {
            System.out.println("Opps! something went wrong, please try again");
        }

    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Employee employee = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"));
                }
            }
        }
        return employee;
    }
}
