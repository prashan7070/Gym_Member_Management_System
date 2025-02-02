package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.EmployeeBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.EmployeeDAO;
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeB0Impl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public String generateId() throws SQLException {
        return employeeDAO.generateId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees){
            employeeDTOS.add(new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getContactInfo(),employee.getEmail(),employee.getRole(),employee.getHireDate()));

        }

        return employeeDTOS;
    }

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getContactInfo(),employeeDTO.getEmail(),employeeDTO.getRole(),employeeDTO.getHireDate()));
    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployeeId(),employeeDTO.getName(),employeeDTO.getContactInfo(),employeeDTO.getEmail(),employeeDTO.getRole(),employeeDTO.getHireDate()));

    }

    @Override
    public EmployeeDTO getEmployeeEntityById(String employeeId) throws SQLException {
        Employee employee =  employeeDAO.getEmployeeEntityById(employeeId);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getContactInfo(),employee.getEmail(),employee.getRole(),employee.getHireDate());
    }
}
