package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;

import java.sql.SQLException;

public interface DashBoardBO extends SuperBO {

    int getCountMem() throws SQLException;

    int getCountBooking() throws SQLException;

    int getCountSessions() throws SQLException;

}
