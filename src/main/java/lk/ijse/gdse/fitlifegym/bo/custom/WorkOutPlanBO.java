package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutPlanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkOutPlanBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<WorkOutPlanDTO> getAll() throws SQLException;

    boolean save(WorkOutPlanDTO workOutPlanDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(WorkOutPlanDTO workOutPlanDTO) throws SQLException;

    String getMemberNameByWorkOutPlanId(String workOutPlanId) throws SQLException;

}
