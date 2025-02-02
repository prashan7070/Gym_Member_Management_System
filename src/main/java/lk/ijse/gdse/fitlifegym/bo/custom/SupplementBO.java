package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplement;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplementBO {

    Supplement getSupplementDtoById(String supplementId) throws SQLException;

    boolean isExistsSupplement(SupplementDTO currentSupplementDTO) throws SQLException;

    boolean isStatusUnavailable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

    boolean updateSupplementDetailsTable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException;

    String generateId() throws SQLException;

    ArrayList<SupplementDTO> getAll() throws SQLException;

    boolean save(SupplementDTO supplementDTO) throws SQLException;






}
