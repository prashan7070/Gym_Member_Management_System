package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.SupplementBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.SupplementDAO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplement;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementBOImpl implements SupplementBO {

    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLEMENT);

    @Override
    public SupplementDTO getSupplementDtoById(String supplementId) throws SQLException {
        Supplement supplement = supplementDAO.getSupplementDtoById(supplementId);
        return new SupplementDTO(supplement.getSupplementId(),supplement.getName(),supplement.getDosage(),supplement.getPrice(),supplement.getQty(),supplement.getStatus());
    }

    @Override
    public boolean isExistsSupplement(SupplementDTO currentSupplementDTO) throws SQLException {
        return supplementDAO.isExistsSupplement(new Supplement(currentSupplementDTO.getSupplementId(),currentSupplementDTO.getName(),currentSupplementDTO.getDosage(),currentSupplementDTO.getPrice(),currentSupplementDTO.getQty(),currentSupplementDTO.getStatus()));
    }

    @Override
    public boolean isStatusUnavailable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return supplementDAO.isStatusUnavailable(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));
    }

    @Override
    public boolean updateSupplementDetailsTable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return supplementDAO.updateSupplementDetailsTable(new SupplementSupply(supplementSupplyDTO.getOrderId(),supplementSupplyDTO.getSupplementId(),supplementSupplyDTO.getSupplierId(),supplementSupplyDTO.getQtyOrdered(),supplementSupplyDTO.getUnitCost(),supplementSupplyDTO.getDeliveryCost(),supplementSupplyDTO.getTotalCost(),supplementSupplyDTO.getOrderDate(),supplementSupplyDTO.getOrderStatus()));

    }

    @Override
    public String generateId() throws SQLException {
        String id = supplementDAO.generateId();

        if (id!=null){
            String subString = id.substring(2);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("SM%03d",newIndex);
        }

        return "SM001";
    }

    @Override
    public ArrayList<SupplementDTO> getAll() throws SQLException {
        ArrayList<Supplement> supplements = supplementDAO.getAll();
        ArrayList<SupplementDTO> supplementDTOS = new ArrayList<>();

        for (Supplement supplement : supplements){
            supplementDTOS.add(new SupplementDTO(supplement.getSupplementId(),supplement.getName(),supplement.getDosage(),supplement.getPrice(),supplement.getQty(),supplement.getStatus()));
        }

        return supplementDTOS;
    }

    @Override
    public boolean save(SupplementDTO supplementDTO) throws SQLException {
        return supplementDAO.save(new Supplement(supplementDTO.getSupplementId(),supplementDTO.getName(),supplementDTO.getDosage(),supplementDTO.getPrice(),supplementDTO.getQty(),supplementDTO.getStatus()));
    }
}
