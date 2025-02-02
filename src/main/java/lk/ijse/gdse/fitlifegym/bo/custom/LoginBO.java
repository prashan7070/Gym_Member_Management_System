package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.entity.Admin;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    AdminDto checkUserLoginInfo(String username) throws SQLException;

    String generateId() throws SQLException;

    boolean save(AdminDto adminDto) throws SQLException;


}
