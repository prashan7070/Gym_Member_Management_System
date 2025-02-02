package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Equipment;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.SQLException;

public interface EquipmentDAO extends CrudDAO<Equipment> {


     Equipment getEquipmentEntityById(String equipmentId) throws SQLException;

     boolean isExistsEquipment(Equipment currentEquipment) throws SQLException;

     boolean isStatusUnavailable(EquipmentSupply equipmentSupply) throws SQLException;

     boolean isUpdateQtyInEquipment(EquipmentSupply equipmentSupply) throws SQLException;

     boolean updateUsingEquipSupply(EquipmentSupply equipmentSupply) throws SQLException;



}
