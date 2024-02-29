package JdbcProj;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
        System.out.println("Enter which action you would like to do by choosing a number 1-6");
        System.out.println("1, addEmployee");
        System.out.println("2, updateEmployee");
        System.out.println("3, deleteEmployee");
        System.out.println("4, getAllEmployees");
        System.out.println("5, getEmployeeById");
        System.out.println("6, exit");
        boolean shouldContinue = true;
        int currentAction = input.nextInt();
        String name = "";
        String email = "";
        int id = 0;
        // Possible transaction
        while (shouldContinue) {
            switch (currentAction) {
                case 1:
                    System.out.println("Enter the name of the new employee to insert");
                    input.nextLine();
                    name = input.nextLine();
                    System.out.println("Enter the email");
                    email = input.nextLine();
                    employeeDao.addEmployee(new Employee(name, email));
                    break;
                case 2:
                    System.out.println("Enter the id of the employee to update");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the updated name");
                    name = input.nextLine();
                    System.out.println("Enter the email name");
                    email = input.nextLine();
                    employeeDao.updateEmployee(id, name, email);
                    break;
                case 3:
                    System.out.println("Enter the id of the employee to delete");
                    id = input.nextInt();
                    employeeDao.deleteEmployee(id);
                    break;
                case 4:
                    List<Employee> employeeList = employeeDao.getEmployees();
                    for (Employee emp : employeeList) {
                        System.out.println("Id: " + emp.getId() +
                                " Name: " + emp.getName() +
                                " Employee: " + emp.getEmail());
                    }
                    break;
                case 5:
                    System.out.println("Enter the id of the employee to retrieve");
                    id = input.nextInt();
                    Employee outputEmp = employeeDao.getEmployeeById(id);
                    System.out.println("Id: " + outputEmp.getId() +
                                        " Name: " + outputEmp.getName() +
                                        " Employee: " + outputEmp.getEmail());
                    break;
                case 6:
                    shouldContinue = false;
                    break;
            }
            System.out.println("Enter which action you would like to do by choosing a number 1-6");
            System.out.println("1, addEmployee");
            System.out.println("2, updateEmployee");
            System.out.println("3, deleteEmployee");
            System.out.println("4, getAllEmployees");
            System.out.println("5, getEmployeeById");
            System.out.println("6, exit");
            currentAction = input.nextInt();
        }
/*
        // set the data to insert in database
        Employee employee = new Employee();
        employee.setId(10);
        employee.setName("Mark");
        employee.setEmail("mo@gmail.com");

        employee.setId(10);
        employee.setName("Mark");
        employee.setEmail("mark2@gmail.com");
        EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
        /*
        try {
            employeeDao.addEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            employeeDao.updateEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        List<Employee> output = employeeDao.getEmployees();
        for(Employee emp: output) {
            System.out.println(emp.getName());
        }


        Employee output = employeeDao.getEmployeeById(1);
        System.out.println(output.getEmail());
        */
    }
}