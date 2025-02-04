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
    public AdminDto validateLogin(String username, String password) throws SQLException {
        Admin admin = adminDAO.checkUserLoginInfo(username);

        if (admin!=null && admin.getPassword().equals(password)){
            return new AdminDto(admin.getUserLoginId(),admin.getUsername(),admin.getEmail(),admin.getPassword(),admin.getRole());

        }

        return null;

    }

    @Override
    public String generateId() throws SQLException {
        String id = adminDAO.generateId();

        if (id!=null){

            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("U%03d", newIdIndex);

        }

        return "U001";
    }

    @Override
    public boolean save(AdminDto adminDto) throws SQLException {
        return adminDAO.save(new Admin(adminDto.getUserLoginId(),adminDto.getUsername(),adminDto.getEmail(),adminDto.getPassword(),adminDto.getRole()));

    }

    @Override
    public boolean isUsernameVlaid(String userName) throws SQLException {
        return adminDAO.checkUserLoginInfo(userName) != null;
    }


}
