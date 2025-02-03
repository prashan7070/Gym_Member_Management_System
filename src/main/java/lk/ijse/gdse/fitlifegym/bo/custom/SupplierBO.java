package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<SupplierDTO> getAll() throws SQLException;

    SupplierDTO getSupplierEntityBySupplierId(String supplierId) throws SQLException;

    boolean save(SupplierDTO supplierDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(SupplierDTO supplierDTO) throws SQLException;

}
