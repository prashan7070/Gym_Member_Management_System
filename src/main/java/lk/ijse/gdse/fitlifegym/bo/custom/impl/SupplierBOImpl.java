package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.SupplierBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.SupplierDAO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);


    @Override
    public String generateId() throws SQLException {
        String id = supplierDAO.generateId();

        if (id!=null){
            String subString = id.substring(2);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("SU%03d",newIndex);
        }

        return "SU001";
    }

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : suppliers){
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getDescription(),supplier.getContactInfo(),supplier.getAddress()));

        }

        return supplierDTOS;

    }

    @Override
    public SupplierDTO getSupplierEntityBySupplierId(String supplierId) throws SQLException {
        Supplier supplier = supplierDAO.getSupplierEntityBySupplierId(supplierId);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getDescription(),supplier.getContactInfo(),supplier.getAddress());
    }
}
