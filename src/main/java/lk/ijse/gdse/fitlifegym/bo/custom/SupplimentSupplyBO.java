package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplimentSupplyBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<SupplementSupplyDTO> getAll() throws SQLException;

    boolean save(SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

    boolean update(SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

    boolean savePendingSupplyOrder(SupplementDTO supplementDTO, SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

    boolean confirmSupplyOrder(SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

}
