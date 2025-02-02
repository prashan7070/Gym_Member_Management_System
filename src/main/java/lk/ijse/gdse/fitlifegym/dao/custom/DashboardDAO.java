package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;

import java.sql.SQLException;

public interface DashboardDAO extends CrudDAO {

     int getCountMem() throws SQLException;

     int getCountBooking() throws SQLException;

     int getCountSessions() throws SQLException;

}
