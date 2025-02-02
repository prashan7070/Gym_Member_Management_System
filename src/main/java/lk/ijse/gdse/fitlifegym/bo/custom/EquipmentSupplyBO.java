package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EquipmentSupplyBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<EquipmentSupplyDTO> getAll() throws SQLException;

    boolean save(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    boolean update(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    boolean savePendingSupplyOrder(EquipmentDTO currentEquipmentDTO, EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;

    boolean confirmSupplyOrder(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException;







}
