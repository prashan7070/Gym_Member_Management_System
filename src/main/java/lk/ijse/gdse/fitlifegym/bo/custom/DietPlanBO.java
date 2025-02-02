package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.DietPlanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DietPlanBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<DietPlanDTO> getAll() throws SQLException;

    boolean save(DietPlanDTO dietPlanDTO) throws SQLException;

    boolean delete(String dietPlanId) throws SQLException;

    boolean update(DietPlanDTO dietPlanDTO) throws SQLException;



}
