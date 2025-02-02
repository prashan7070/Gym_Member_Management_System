package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dao.custom.QueryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {

    public String getMemberNameByWorkOutPlanId(String workOutPlanId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT m.name FROM member m JOIN workoutplan w ON m.memberId=w.memberId WHERE w.workoutplanId=?",workOutPlanId);

        if (rst.next()){
            return rst.getString("name");

        }

        return null;
    }

}
