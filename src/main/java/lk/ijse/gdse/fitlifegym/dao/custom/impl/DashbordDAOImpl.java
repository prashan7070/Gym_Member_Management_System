package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.DashboardDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DashbordDAOImpl implements DashboardDAO {

    public int getCountMem() throws SQLException {

        ResultSet rst = SQLUtil.execute("select COUNT(memberId) AS count from member");

        int i = 0;

        if(rst.next()){
            i = Integer.parseInt(rst.getString(1));
        }
        return i;
    }

    public int getCountBooking() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT COUNT(bookingId) AS count FROM booking WHERE date >= CURDATE();");

        int i = 0;

        if(rst.next()){
            i = Integer.parseInt(rst.getString(1));
        }
        return i;
    }


    public int getCountSessions() throws SQLException {

        ResultSet rst = SQLUtil.execute("select COUNT(sessionsId) AS count from sessions WHERE date >= CURDATE();");

        int i = 0;

        if(rst.next()){
            i = Integer.parseInt(rst.getString(1));
        }
        return i;

    }


    @Override
    public String generateId() throws SQLException {
        return "";
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Object o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object o) throws SQLException {
        return false;
    }
}
