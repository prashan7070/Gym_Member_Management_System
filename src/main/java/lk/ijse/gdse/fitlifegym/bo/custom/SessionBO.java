package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.dto.SessionDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionBO {

    String generateId() throws SQLException;

    ArrayList<SessionDTO> getAll() throws SQLException;

    boolean save(SessionDTO sessionDTO) throws SQLException;

    boolean delete(String sessionId) throws SQLException;

    boolean update(SessionDTO sessionDTO) throws SQLException;



}
