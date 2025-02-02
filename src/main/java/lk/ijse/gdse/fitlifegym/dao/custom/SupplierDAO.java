package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {


     Supplier getSupplierEntityBySupplierId(String supplierId) throws SQLException;



}
