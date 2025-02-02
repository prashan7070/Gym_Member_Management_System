package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.WorkOutEquipBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.WorkOutEquipDAO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutEquipDTO;
import lk.ijse.gdse.fitlifegym.entity.WorkOutEquip;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorkOutEquipBOImpl implements WorkOutEquipBO {

    WorkOutEquipDAO workOutEquipDAO = (WorkOutEquipDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.WORK_OUT_EQUIP);


    @Override
    public ArrayList<WorkOutEquipDTO> getAll() throws SQLException {
            ArrayList<WorkOutEquip> workOutEquips = workOutEquipDAO.getAll();
            ArrayList<WorkOutEquipDTO> workOutEquipDTOS = new ArrayList<>();

            for (WorkOutEquip workOutEquip : workOutEquips){
                workOutEquipDTOS.add(new WorkOutEquipDTO(workOutEquip.getEquipmentId(),workOutEquip.getWorkOutPlanId(),workOutEquip.getUsageFrequency(),workOutEquip.getDurationPerSession(),workOutEquip.getInstructions()));
            }

            return workOutEquipDTOS;
    }

    @Override
    public boolean save(WorkOutEquipDTO workOutEquipDTO) throws SQLException {
        return workOutEquipDAO.save(new WorkOutEquip(workOutEquipDTO.getEquipmentId(),workOutEquipDTO.getWorkOutPlanId(),workOutEquipDTO.getUsageFrequency(),workOutEquipDTO.getDurationPerSession(),workOutEquipDTO.getInstructions()));
    }

    @Override
    public boolean delete(String workOutEquipId) throws SQLException {
        return workOutEquipDAO.delete(workOutEquipId);
    }

    @Override
    public boolean update(WorkOutEquipDTO workOutEquipDTO) throws SQLException {
        return workOutEquipDAO.update(new WorkOutEquip(workOutEquipDTO.getEquipmentId(),workOutEquipDTO.getWorkOutPlanId(),workOutEquipDTO.getUsageFrequency(),workOutEquipDTO.getDurationPerSession(),workOutEquipDTO.getInstructions()));

    }
}
