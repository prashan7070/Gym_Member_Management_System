package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.LoginBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.AdminDAO;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.entity.Admin;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    @Override
    public AdminDto checkUserLoginInfo(String username) throws SQLException {
        Admin admin = adminDAO.checkUserLoginInfo(username);
        return new AdminDto(admin.getUserLoginId(),admin.getUsername(),admin.getEmail(),admin.getPassword(),admin.getRole());
    }

    @Override
    public String generateId() throws SQLException {
        return adminDAO.generateId();
    }

    @Override
    public boolean save(AdminDto adminDto) throws SQLException {
        return adminDAO.save(new Admin(adminDto.getUserLoginId(),adminDto.getUsername(),adminDto.getEmail(),adminDto.getPassword(),adminDto.getRole()));

    }


}
