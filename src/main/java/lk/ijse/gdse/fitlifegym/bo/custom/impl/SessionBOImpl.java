package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.SessionBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.SessionDAO;
import lk.ijse.gdse.fitlifegym.dto.SessionDTO;
import lk.ijse.gdse.fitlifegym.entity.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class SessionBOImpl implements SessionBO {

    SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SESSION);


    @Override
    public String generateId() throws SQLException {
            String id =  sessionDAO.generateId();
            if (id!=null){
                String subString = id.substring(1);
                int i = Integer.parseInt(subString);
                int newIndex = i+1;
                return String.format("S%03d",newIndex);

            }

            return "S001";
    }

    @Override
    public ArrayList<SessionDTO> getAll() throws SQLException {
        ArrayList<Session> sessions = sessionDAO.getAll();
        ArrayList<SessionDTO> sessionDTOS = new ArrayList<>();

        for (Session session : sessions){
            sessionDTOS.add(new SessionDTO(session.getSessionId(),session.getEmployeeId(),session.getDate(),session.getTime(),session.getDescription()));
        }

        return sessionDTOS;
    }


    @Override
    public boolean save(SessionDTO sessionDTO) throws SQLException {
        return sessionDAO.save(new Session(sessionDTO.getSessionId(),sessionDTO.getEmployeeId(),sessionDTO.getDate(),sessionDTO.getTime(),sessionDTO.getDescription()));
    }

    @Override
    public boolean delete(String sessionId) throws SQLException {
        return sessionDAO.delete(sessionId);
    }

    @Override
    public boolean update(SessionDTO sessionDTO) throws SQLException {
        return sessionDAO.update(new Session(sessionDTO.getSessionId(),sessionDTO.getEmployeeId(),sessionDTO.getDate(),sessionDTO.getTime(),sessionDTO.getDescription()));

    }


}
