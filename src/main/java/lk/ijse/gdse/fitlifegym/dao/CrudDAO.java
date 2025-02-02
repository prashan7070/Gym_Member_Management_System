package lk.ijse.gdse.fitlifegym.dao;

import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

     String generateId() throws SQLException;

     ArrayList<T> getAll() throws SQLException;

     boolean save(T t) throws SQLException;

     boolean delete(String T) throws SQLException;

     boolean update(T t) throws SQLException;



}
