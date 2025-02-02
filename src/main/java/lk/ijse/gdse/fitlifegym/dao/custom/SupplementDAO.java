package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplement;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplementDAO extends CrudDAO<Supplement> {


     Supplement getSupplementDtoById(String supplementId) throws SQLException;

     boolean isExistsSupplement(Supplement currentSupplement) throws SQLException;

     boolean isStatusUnavailable(SupplementSupply supplementSupply) throws SQLException;

     boolean updateSupplementDetailsTable(SupplementSupply supplementSupply) throws SQLException;

     boolean isUpdateQtyInSupplement(SupplementSupply supplementSupply) throws SQLException;


//     boolean deleteEquipment(String equipmentId) throws SQLException;

}
