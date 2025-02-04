package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.EmployeeDAO;
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT employeeId FROM employee ORDER BY employeeId DESC LIMIT 1");

        if (rst.next()){
            return rst.getString(1);

        }

        return null;
    }


    public ArrayList<Employee> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> employees = new ArrayList<>();

        while (rst.next()){

            Employee employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            );

            employees.add(employee);


        }

        return employees;

    }

    public boolean save(Employee employee) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getContactInfo(),
                employee.getEmail(),
                employee.getRole(),
                employee.getHireDate()


        );
    }

    public boolean delete(String employeeId) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE employeeId=?",employeeId);
    }


    public boolean update(Employee employee) throws SQLException {
            return SQLUtil.execute("UPDATE employee SET name = ? , contactInfo = ? , email = ? , role = ? ,hireDate = ? WHERE employeeId = ?" ,

                    employee.getName(),
                    employee.getContactInfo(),
                    employee.getEmail(),
                    employee.getRole(),
                    employee.getHireDate(),
                    employee.getEmployeeId()


                    );

    }


    public Employee getEmployeeEntityById(String employeeId) throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT employeeId , name , contactInfo , email , role , hireDate FROM employee WHERE employeeId = ?",employeeId);

        if (rst.next()){
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

            );


        }

        return null;


    }
}
