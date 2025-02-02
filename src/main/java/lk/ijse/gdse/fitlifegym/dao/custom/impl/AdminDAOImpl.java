package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.AdminDAO;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    public Admin checkUserLoginInfo(String username) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user_login WHERE username=?",username);

        while (resultSet.next()){
            Admin admin = new Admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            return admin;
        }

        return null;
    }


    public boolean save(Admin admin) throws SQLException {

        return SQLUtil.execute("INSERT INTO user_login VALUES (?,?,?,?,?)",
                admin.getUserLoginId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole()

        );

    }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Admin admin) throws SQLException {
        return false;
    }

    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT userLoginId FROM user_login ORDER BY userLoginId DESC LIMIT 1;");


            if (rst.next()) {
                String lastId = rst.getString(1);
                String substring = lastId.substring(1);
                int i = Integer.parseInt(substring);
                int newIdIndex = i + 1;
                return String.format("U%03d", newIdIndex);
            }

        return "U001";

    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        return null;
    }


}
