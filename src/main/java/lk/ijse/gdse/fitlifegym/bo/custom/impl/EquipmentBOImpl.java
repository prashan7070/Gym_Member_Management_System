package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.bo.custom.EquipmentBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.EquipmentDAO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Equipment;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentBOImpl implements EquipmentBO {

    EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EQUIPMENT);


    @Override
    public EquipmentDTO getEquipmentEntityById(String equipmentId) throws SQLException {

        Equipment equipment = equipmentDAO.getEquipmentEntityById(equipmentId);
        return new EquipmentDTO(equipment.getEquipmentId(),equipment.getName(),equipment.getDescription(),equipment.getQuantity(),equipment.getStatus());

    }

    @Override
    public boolean isExistsEquipment(EquipmentDTO currentEquipmentDto) throws SQLException {
        return equipmentDAO.isExistsEquipment(new Equipment(currentEquipmentDto.getEquipmentId(),currentEquipmentDto.getName(),currentEquipmentDto.getDescription(),currentEquipmentDto.getQuantity(),currentEquipmentDto.getStatus()));
    }

    @Override
    public boolean isStatusUnavailable(EquipmentSupplyDTO equipmentSupplyDto) throws SQLException {
        return equipmentDAO.isStatusUnavailable(new EquipmentSupply(equipmentSupplyDto.getOrderId(),equipmentSupplyDto.getEquipmentId(),equipmentSupplyDto.getSupplierId(),equipmentSupplyDto.getQuantityOrdered(),equipmentSupplyDto.getUnitCost(),equipmentSupplyDto.getDeliveryCost(),equipmentSupplyDto.getTotalCost(),equipmentSupplyDto.getOrderDate(),equipmentSupplyDto.getOrderStatus()));
    }

    @Override
    public boolean isUpdateQtyInEquipment(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
        return equipmentDAO.isUpdateQtyInEquipment(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

    }

    @Override
    public boolean updateUsingEquipSupply(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
        return equipmentDAO.updateUsingEquipSupply(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));
    }

    @Override
    public String generateNewEquipmentId() throws SQLException {
        String id = equipmentDAO.generateId();
        if (id!=null){
            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("EQ%03d",newIndex);
        }

        return "EQ001";

    }

    @Override
    public ArrayList<EquipmentDTO> getAllEquipments() throws SQLException {
        ArrayList<Equipment> equipments = equipmentDAO.getAll();
        ArrayList<EquipmentDTO> equipmentDTOS = new ArrayList<>();

        for (Equipment equipment : equipments){
            equipmentDTOS.add(new EquipmentDTO(equipment.getEquipmentId(),equipment.getName(),equipment.getDescription(),equipment.getQuantity(),equipment.getStatus()));
        }

        return equipmentDTOS;
    }

    @Override
    public boolean saveEquipment(EquipmentDTO equipmentDTO) throws SQLException {
        return equipmentDAO.save(new Equipment(equipmentDTO.getEquipmentId(),equipmentDTO.getName(),equipmentDTO.getDescription(),equipmentDTO.getQuantity(),equipmentDTO.getStatus()));
    }

    @Override
    public boolean delete(String equipmentId) throws SQLException {
        return equipmentDAO.delete(equipmentId);
    }


}
