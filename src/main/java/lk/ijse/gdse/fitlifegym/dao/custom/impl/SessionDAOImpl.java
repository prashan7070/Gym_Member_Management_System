package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.SessionDAO;
import lk.ijse.gdse.fitlifegym.dto.SessionDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SessionDAOImpl implements SessionDAO {

    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT sessionsId FROM sessions ORDER BY sessionsId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String subString = lastId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i + 1;
            return String.format("S%03d", newIndex);
        }

        return "S001";

    }


    public ArrayList<Session> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM sessions");

        ArrayList<Session> sessions = new ArrayList<>();

        while (rst.next()) {

            Session session = new Session(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );

            sessions.add(session);

        }

        return sessions;

    }


    public boolean delete(String sessionId) throws SQLException {

        return SQLUtil.execute("DELETE FROM sessions WHERE sessionsId = ?", sessionId);


    }


    public boolean save(Session session) throws SQLException {
        return SQLUtil.execute("INSERT INTO sessions VALUES(?,?,?,?,?)",

                session.getSessionId(),
                session.getEmployeeId(),
                session.getDate(),
                session.getTime(),
                session.getDescription()

        );
    }


    public boolean update(Session session) throws SQLException {
        return SQLUtil.execute("UPDATE sessions SET employeeId=? , date=? , time=? ,description=? WHERE sessionsId=?",

                session.getEmployeeId(),
                session.getDate(),
                session.getTime(),
                session.getDescription(),
                session.getSessionId()

        );

    }

}