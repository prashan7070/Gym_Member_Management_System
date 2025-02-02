package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.DashBoardBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.DashboardDAO;

import java.sql.SQLException;

public class DashBoardBOImpl implements DashBoardBO {

    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DASHBOARD);


    @Override
    public int getCountMem() throws SQLException {
        return dashboardDAO.getCountMem();
    }


    @Override
    public int getCountBooking() throws SQLException {
        return dashboardDAO.getCountBooking();
    }


    @Override
    public int getCountSessions() throws SQLException {
        return dashboardDAO.getCountSessions();
    }
}
