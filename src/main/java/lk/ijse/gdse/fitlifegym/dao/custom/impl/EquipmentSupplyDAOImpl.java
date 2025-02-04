package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.EquipmentSupplyDAO;
import lk.ijse.gdse.fitlifegym.db.DBConnection;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentSupplyDAOImpl implements EquipmentSupplyDAO {





    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT orderId FROM equipmentsupplydetails ORDER BY orderId DESC LIMIT 1");

        return rst.next() ? rst.getString(1) : null;

    }


//    public boolean savePendingSupplyOrder(EquipmentDTO currentEquipmentDTO,EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//
//                connection.setAutoCommit(false);
//
//                boolean isExistsEquipment = equipmentDAOImpl.isExistsEquipment(currentEquipmentDTO);
//
//
//                if (isExistsEquipment){
//
//                    boolean isEquipmentSupplyDetailsSaved = saveOrderSupplyDetails(equipmentSupplyDTO);
//
//                    if (isEquipmentSupplyDetailsSaved){
//
//                        connection.commit();
//                        return true;
//
//
//                    }
//
//
//                }else {
//
//                    boolean isEquipmentDetailsSaved = equipmentDAOImpl.saveEquipmentDetails(currentEquipmentDTO);
//
//                    if (isEquipmentDetailsSaved){
//
//                        boolean isEquipmentSupplyDetailsSaved = saveOrderSupplyDetails(equipmentSupplyDTO);
//
//                        if (isEquipmentSupplyDetailsSaved){
//
//                            connection.commit();
//                            return true;
//
//
//                        }
//
//                    }
//
//
//                }
//
//
//
//                connection.rollback();
//
//                return false;
//
//        } catch (Exception e){
//
//                e.printStackTrace();
//
//                connection.rollback();
//                return false;
//
//        } finally {
//               connection.setAutoCommit(true);
//        }
//
//
//
//
//    }





    public boolean save(EquipmentSupply equipmentSupply) throws SQLException {

        return SQLUtil.execute("INSERT INTO equipmentsupplydetails VALUES(?,?,?,?,?,?,?,?,?)",

                equipmentSupply.getOrderId(),
                equipmentSupply.getEquipmentId(),
                equipmentSupply.getSupplierId(),
                equipmentSupply.getQuantityOrdered(),
                equipmentSupply.getUnitCost(),
                equipmentSupply.getDeliveryCost(),
                equipmentSupply.getTotalCost(),
                equipmentSupply.getOrderDate(),
                equipmentSupply.getOrderStatus()



        );

    }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }


    public ArrayList<EquipmentSupply> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM equipmentsupplydetails");

        ArrayList<EquipmentSupply> equipmentSupplies = new ArrayList<>();

                while (rst.next()){
                    EquipmentSupply equipmentSupply = new EquipmentSupply(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getDouble(5),
                        rst.getDouble(6),
                        rst.getDouble(7),
                        rst.getString(8),
                        rst.getString(9)
                    );
                    equipmentSupplies.add(equipmentSupply);


                }

                return equipmentSupplies;


    }


//    public boolean confirmSupplyOrder(EquipmentSupplyDTO equipmentSupplyDTO) throws SQLException {
//
//
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            try {
//
//                        connection.setAutoCommit(false);
//
//
//                        boolean checkEquipmentStatusIsUnavailable = equipmentDAOImpl.isStatusUnavailable(equipmentSupplyDTO);
//
//                        if (checkEquipmentStatusIsUnavailable){
//
//                                boolean updateEquipmentSupplyDetailsTable = updateEquipmentSupplyDetails(equipmentSupplyDTO);
//
//                                if (updateEquipmentSupplyDetailsTable){
//
//                                    boolean updateEquipmentDetailsTable = equipmentDAOImpl.updateEquipmentDetailsTable(equipmentSupplyDTO);
//
//                                    if (updateEquipmentDetailsTable){
//
//                                        connection.commit();
//                                        return true;
//
//                                    }
//
//
//                                }
//
//
//                        }else {
//
//
//                                boolean isUpdateQtyInEquipment = equipmentDAOImpl.isUpdateQtyInEquipment(equipmentSupplyDTO);
//
//                                if (isUpdateQtyInEquipment){
//
//                                    boolean updateEquipmentSupplyDetailsTable = updateEquipmentSupplyDetails(equipmentSupplyDTO);
//
//                                    if (updateEquipmentSupplyDetailsTable){
//
//                                        connection.commit();
//                                        return true;
//
//
//                                    }
//
//
//                                }
//
//
//                        }
//
//
//                        connection.rollback();
//
//                        return false;
//
//
//            } catch (Exception e){
//
//                        e.printStackTrace();
//
//                        connection.rollback();
//                        return false;
//
//            } finally {
//
//                        connection.setAutoCommit(true);
//            }
//
//
//    }

    public boolean update(EquipmentSupply equipmentSupply) throws SQLException {

        return SQLUtil.execute("UPDATE equipmentsupplydetails SET orderStatus=? WHERE orderId=?",

                equipmentSupply.getOrderStatus(),
                equipmentSupply.getOrderId()

                );


    }










}
