package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.SupplementSupplyDAO;
import lk.ijse.gdse.fitlifegym.db.DBConnection;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementSupplyDAOImpl implements SupplementSupplyDAO {



    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT orderId FROM supplementsupplydetails ORDER BY orderId DESC LIMIT 1");

        if (rst.next()){
            return rst.getString(1);
        }

        return null;

    }


     public boolean save(SupplementSupply supplementSupply) throws SQLException {

        return SQLUtil.execute("INSERT INTO supplementsupplydetails VALUES(?,?,?,?,?,?,?,?,?)",

                supplementSupply.getOrderId(),
                supplementSupply.getSupplementId(),
                supplementSupply.getSupplierId(),
                supplementSupply.getQtyOrdered(),
                supplementSupply.getUnitCost(),
                supplementSupply.getDeliveryCost(),
                supplementSupply.getTotalCost(),
                supplementSupply.getOrderDate(),
                supplementSupply.getOrderStatus()



        );

    }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }


    public ArrayList<SupplementSupply> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM supplementsupplydetails");

        ArrayList<SupplementSupply> supplementSupplies = new ArrayList<>();

        while (rst.next()){
            SupplementSupply supplementSupply = new SupplementSupply(
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
            supplementSupplies.add(supplementSupply);


        }

        return supplementSupplies;


    }



    public boolean update(SupplementSupply supplementSupply) throws SQLException {

        return SQLUtil.execute("UPDATE supplementsupplydetails SET orderStatus=? WHERE orderId=?",

                supplementSupply.getOrderStatus(),
                supplementSupply.getOrderId()

        );


    }


//    public boolean savePendingSupplyOrder(SupplementDTO supplementDTO, SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//
//            connection.setAutoCommit(false);
//
//            boolean isExistsSupplement = supplementDAOImpl.isExistsSupplement(supplementDTO);
//
//
//            if (isExistsSupplement){
//
//                boolean isSupplementSupplyDetailsSaved = saveSupplementOrderSupplyDetails(supplementSupplyDTO);
//
//                if (isSupplementSupplyDetailsSaved){
//
//                    connection.commit();
//                    return true;
//
//
//                }
//
//
//            }else {
//
//                boolean isSupplementDetailsSaved = supplementDAOImpl.saveSupplementDetails(supplementDTO);
//
//                if (isSupplementDetailsSaved){
//
//                    boolean isSupplementSupplyDetailsSaved = saveSupplementOrderSupplyDetails(supplementSupplyDTO);
//
//                    if (isSupplementSupplyDetailsSaved){
//
//                        connection.commit();
//                        return true;
//
//
//                    }
//
//                }
//
//
//            }
//
//
//
//            connection.rollback();
//
//            return false;
//
//        } catch (Exception e){
//
//            e.printStackTrace();
//
//            connection.rollback();
//            return false;
//
//        } finally {
//            connection.setAutoCommit(true);
//        }
//
//
//
//
//    }




//    public boolean confirmSupplyOrder(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//
//            connection.setAutoCommit(false);
//
//
//            boolean checkSupplementStatusIsUnavailable = supplementDAOImpl.isStatusUnavailable(supplementSupplyDTO);
//
//            if (checkSupplementStatusIsUnavailable){
//
//                boolean updateSupplementSupplyDetailsTable = updateSupplementSupplyDetails(supplementSupplyDTO);
//
//                if (updateSupplementSupplyDetailsTable){
//
//                    boolean updateSupplementDetailsTable = supplementDAOImpl.updateSupplementDetailsTable(supplementSupplyDTO);
//
//                    if (updateSupplementDetailsTable){
//
//                        connection.commit();
//                        return true;
//
//                    }
//
//
//                }
//
//
//            }else {
//
//
//                boolean isUpdateQtyInSupplement = supplementDAOImpl.isUpdateQtyInSupplement(supplementSupplyDTO);
//
//                if (isUpdateQtyInSupplement){
//
//                    boolean updateSupplementSupplyDetailsTable = updateSupplementSupplyDetails(supplementSupplyDTO);
//
//                    if (updateSupplementSupplyDetailsTable){
//
//                        connection.commit();
//                        return true;
//
//                    }
//
//
//                }
//
//
//            }
//
//
//            connection.rollback();
//
//            return false;
//
//
//        } catch (Exception e){
//
//            e.printStackTrace();
//
//            connection.rollback();
//            return false;
//
//        } finally {
//
//            connection.setAutoCommit(true);
//        }
//
//
//
//    }



}
