package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    AdminDto validateLogin(String username, String password) throws SQLException;

    String generateId() throws SQLException;

    boolean save(AdminDto adminDto) throws SQLException;


    boolean isUsernameVlaid(String userName) throws SQLException;
}
