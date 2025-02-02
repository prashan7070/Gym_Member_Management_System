package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Equipment;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquipmentBO extends SuperBO {

    EquipmentDTO getEquipmentEntityById(String equipmentId) throws SQLException;

    boolean isExistsEquipment(EquipmentDTO currentEquipmentDto) throws SQLException;

    boolean isStatusUnavailable(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    boolean isUpdateQtyInEquipment(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    boolean updateUsingEquipSupply(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    String generateNewEquipmentId() throws SQLException;

    ArrayList<EquipmentDTO> getAllEquipments() throws SQLException;

    boolean saveEquipment(EquipmentDTO equipmentDTO) throws SQLException;

    boolean delete(String equipmentId) throws SQLException;


}
