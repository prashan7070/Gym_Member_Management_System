package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutEquipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkOutEquipBO extends SuperBO {

    ArrayList<WorkOutEquipDTO> getAll() throws SQLException;

    boolean save(WorkOutEquipDTO workOutEquipDTO) throws SQLException;

    boolean delete(String equipmentId,String workOutPlanId) throws SQLException;

    boolean update(WorkOutEquipDTO workOutEquipDTO) throws SQLException;



}
