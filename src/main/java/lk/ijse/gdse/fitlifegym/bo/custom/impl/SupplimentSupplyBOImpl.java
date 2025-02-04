package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.SupplementBO;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplierBO;
import lk.ijse.gdse.fitlifegym.bo.custom.SupplimentSupplyBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.SupplementDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.SupplementSupplyDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.SupplierDAO;
import lk.ijse.gdse.fitlifegym.db.DBConnection;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplement;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;
import lk.ijse.gdse.fitlifegym.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplimentSupplyBOImpl implements SupplimentSupplyBO {

    SupplementSupplyDAO supplementSupplyDAO = (SupplementSupplyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLEMENT_SUPPLY);
    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLEMENT);

    @Override
    public String generateId() throws SQLException {
        String id = supplementSupplyDAO.generateId();

        if (id!=null){

            String subString = id.substring(2);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("SS%03d",newIndex);
        }

        return "SS001";
    }

    @Override
    public ArrayList<SupplementSupplyDTO> getAll() throws SQLException {
        ArrayList<SupplementSupply> supplementSupplies = supplementSupplyDAO.getAll();
        ArrayList<SupplementSupplyDTO> supplementSupplyDTOS = new ArrayList<>();

        for (SupplementSupply supplementSupply : supplementSupplies){
            supplementSupplyDTOS.add(new SupplementSupplyDTO(supplementSupply.getOrderId(),supplementSupply.getSupplementId(),supplementSupply.getSupplierId(),supplementSupply.getQtyOrdered(),supplementSupply.getUnitCost(),supplementSupply.getDeliveryCost(),supplementSupply.getTotalCost(),supplementSupply.getOrderDate(),supplementSupply.getOrderStatus()));
        }

        return supplementSupplyDTOS;

    }

    @Override
    public boolean save(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return supplementSupplyDAO.save(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));
    }

    @Override
    public boolean update(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return supplementSupplyDAO.update(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));

    }

    //First Transaction


    public boolean savePendingSupplyOrder(SupplementDTO supplementDTO, SupplementSupplyDTO supplementSupplyDTO) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);

            boolean isExistsSupplement = supplementDAO.isExistsSupplement(new Supplement(supplementDTO.getSupplementId(),supplementDTO.getName(),supplementDTO.getDosage(),supplementDTO.getPrice(),supplementDTO.getQty(),supplementDTO.getStatus()));


            if (isExistsSupplement){

                boolean isSupplementSupplyDetailsSaved = save(supplementSupplyDTO);

                if (isSupplementSupplyDetailsSaved){

                    connection.commit();
                    return true;


                }


            }else {

                boolean isSupplementDetailsSaved = supplementDAO.save(new Supplement(supplementDTO.getSupplementId(),supplementDTO.getName(),supplementDTO.getDosage(),supplementDTO.getPrice(),supplementDTO.getQty(),supplementDTO.getStatus()));

                if (isSupplementDetailsSaved){

                    boolean isSupplementSupplyDetailsSaved = save(supplementSupplyDTO);

                    if (isSupplementSupplyDetailsSaved){

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




    public boolean confirmSupplyOrder(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);


            boolean checkSupplementStatusIsUnavailable = supplementDAO.isStatusUnavailable(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));

            if (checkSupplementStatusIsUnavailable){

                boolean updateSupplementSupplyDetailsTable = update(supplementSupplyDTO);

                if (updateSupplementSupplyDetailsTable){

                    boolean updateSupplementDetailsTable = supplementDAO.updateSupplementDetailsTable(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));

                    if (updateSupplementDetailsTable){

                        connection.commit();
                        return true;

                    }


                }


            }else {


                boolean isUpdateQtyInSupplement = supplementDAO.isUpdateQtyInSupplement(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));

                if (isUpdateQtyInSupplement){

                    boolean updateSupplementSupplyDetailsTable = update(supplementSupplyDTO);

                    if (updateSupplementSupplyDetailsTable){

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
