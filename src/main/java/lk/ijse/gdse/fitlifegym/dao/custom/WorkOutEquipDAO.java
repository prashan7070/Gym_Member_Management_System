package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.dto.WorkOutEquipDTO;
import lk.ijse.gdse.fitlifegym.entity.WorkOutEquip;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorkOutEquipDAO extends CrudDAO<WorkOutEquip> {

    boolean delete(String equipmentId,String workOutPlanId) throws SQLException;



}
