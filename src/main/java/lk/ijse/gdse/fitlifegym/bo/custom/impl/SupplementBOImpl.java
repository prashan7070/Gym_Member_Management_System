package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.SupplementBO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.entity.Supplement;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementBOImpl implements SupplementBO {

    @Override
    public Supplement getSupplementDtoById(String supplementId) throws SQLException {
        return null;
    }

    @Override
    public boolean isExistsSupplement(SupplementDTO currentSupplementDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean isStatusUnavailable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean updateSupplementDetailsTable(SupplementSupplyDTO supplementSupplyDTO) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList<SupplementDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(SupplementDTO supplementDTO) throws SQLException {
        return false;
    }
}
