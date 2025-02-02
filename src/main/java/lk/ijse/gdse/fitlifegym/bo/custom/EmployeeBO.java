package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<EmployeeDTO> getAll() throws SQLException;

    boolean save(EmployeeDTO employeeDTO) throws SQLException;

    boolean delete(String employeeId) throws SQLException;

    boolean update(EmployeeDTO employeeDTO) throws SQLException;

    EmployeeDTO getEmployeeEntityById(String employeeId) throws SQLException;

}
