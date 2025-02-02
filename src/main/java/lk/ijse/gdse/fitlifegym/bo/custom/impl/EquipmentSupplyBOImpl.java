package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.EquipmentSupplyBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.EquipmentDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.EquipmentSupplyDAO;
import lk.ijse.gdse.fitlifegym.db.DBConnection;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Equipment;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentSupplyBOImpl implements EquipmentSupplyBO {

    EquipmentSupplyDAO equipmentSupplyDAO = (EquipmentSupplyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EQUIPMENT_SUPPLY);
    EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EQUIPMENT);


    @Override
    public String generateId() throws SQLException {

            String id = equipmentSupplyDAO.generateId();

            if (id!=null){
                String subString = id.substring(2);
                int i = Integer.parseInt(subString);
                int newIndex = i+1;
                return String.format("ES%03d",newIndex);
            }

            return "ES001";

    }

    @Override
    public ArrayList<EquipmentSupplyDTO> getAll() throws SQLException {
        ArrayList<EquipmentSupply> equipmentSupplies = equipmentSupplyDAO.getAll();
        ArrayList<EquipmentSupplyDTO> equipmentSupplyDTOS = new ArrayList<>();

        for (EquipmentSupply equipmentSupply : equipmentSupplies){
            equipmentSupplyDTOS.add(new EquipmentSupplyDTO(equipmentSupply.getOrderId(),equipmentSupply.getEquipmentId(),equipmentSupply.getSupplierId(),equipmentSupply.getQuantityOrdered(),equipmentSupply.getUnitCost(),equipmentSupply.getDeliveryCost(),equipmentSupply.getTotalCost(),equipmentSupply.getOrderDate(),equipmentSupply.getOrderStatus()));
        }

        return equipmentSupplyDTOS;

    }

    @Override
    public boolean save(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
        return equipmentSupplyDAO.save(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));
    }

    @Override
    public boolean update(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
        return equipmentSupplyDAO.update(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

    }


    //First Transaction



    public boolean savePendingSupplyOrder(EquipmentDTO currentEquipmentDTO, EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            boolean isExistsEquipment = equipmentDAO.isExistsEquipment(new Equipment(currentEquipmentDTO.getEquipmentId(),currentEquipmentDTO.getName(),currentEquipmentDTO.getDescription(),currentEquipmentDTO.getQuantity(),currentEquipmentDTO.getStatus()));


            if (isExistsEquipment){

                boolean isEquipmentSupplyDetailsSaved = equipmentSupplyDAO.save(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

                if (isEquipmentSupplyDetailsSaved){

                    connection.commit();
                    return true;


                }


            }else {

                boolean isEquipmentDetailsSaved = equipmentDAO.save(new Equipment(currentEquipmentDTO.getEquipmentId(),currentEquipmentDTO.getName(),currentEquipmentDTO.getDescription(),currentEquipmentDTO.getQuantity(),currentEquipmentDTO.getStatus()));

                if (isEquipmentDetailsSaved){

                    boolean isEquipmentSupplyDetailsSaved = equipmentSupplyDAO.save(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));;

                    if (isEquipmentSupplyDetailsSaved){

                        connection.commit();
                        return true;


                    }

                }


            }



            connection.rollback();

            return false;

        } catch (Exception e){

            e.printStackTrace();

            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }


    }



    //Second Transaction


    public boolean confirmSupplyOrder(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {


        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);


            boolean checkEquipmentStatusIsUnavailable = equipmentDAO.isStatusUnavailable(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

            if (checkEquipmentStatusIsUnavailable){

                boolean updateEquipmentSupplyDetailsTable = update(equipmentSupplyDTO);

                if (updateEquipmentSupplyDetailsTable){

                    boolean updateEquipmentDetailsTable = equipmentDAO.updateUsingEquipSupply(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

                    if (updateEquipmentDetailsTable){

                        connection.commit();
                        return true;

                    }


                }


            }else {


                boolean isUpdateQtyInEquipment = equipmentDAO.isUpdateQtyInEquipment(new EquipmentSupply(equipmentSupplyDTO.getOrderId(),equipmentSupplyDTO.getEquipmentId(),equipmentSupplyDTO.getSupplierId(),equipmentSupplyDTO.getQuantityOrdered(),equipmentSupplyDTO.getUnitCost(),equipmentSupplyDTO.getDeliveryCost(),equipmentSupplyDTO.getTotalCost(),equipmentSupplyDTO.getOrderDate(),equipmentSupplyDTO.getOrderStatus()));

                if (isUpdateQtyInEquipment){

                    boolean updateEquipmentSupplyDetailsTable = update(equipmentSupplyDTO);

                    if (updateEquipmentSupplyDetailsTable){

                        connection.commit();
                        return true;


                    }


                }


            }


            connection.rollback();

            return false;


        } catch (Exception e){

            e.printStackTrace();

            connection.rollback();
            return false;

        } finally {

            connection.setAutoCommit(true);
        }


    }




















}
