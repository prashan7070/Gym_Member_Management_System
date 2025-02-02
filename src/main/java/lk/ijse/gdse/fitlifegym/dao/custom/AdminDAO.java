package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dto.AdminDto;
import lk.ijse.gdse.fitlifegym.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin> {

     Admin checkUserLoginInfo(String username) throws SQLException;


}
