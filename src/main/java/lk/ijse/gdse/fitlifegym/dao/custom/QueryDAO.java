package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dao.SuperDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {

     String getMemberNameByWorkOutPlanId(String workOutPlanId) throws SQLException;

}
