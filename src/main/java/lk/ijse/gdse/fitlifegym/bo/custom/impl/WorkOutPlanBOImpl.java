package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.WorkOutEquipBO;
import lk.ijse.gdse.fitlifegym.bo.custom.WorkOutPlanBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.QueryDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.WorkOutPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutEquipDTO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.WorkOutPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorkOutPlanBOImpl implements WorkOutPlanBO {

    WorkOutPlanDAO workOutPlanDAO = (WorkOutPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.WORK_OUT_PLAN);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);


    @Override
    public String generateId() throws SQLException {
        String id = workOutPlanDAO.generateId();

        if (id!=null){
            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("W%03d",newIndex);
        }

        return "W001";
    }

    @Override
    public ArrayList<WorkOutPlanDTO> getAll() throws SQLException {
        ArrayList<WorkOutPlan> workOutPlans = workOutPlanDAO.getAll();
        ArrayList<WorkOutPlanDTO> workOutPlanDTOS = new ArrayList<>();

        for (WorkOutPlan workOutPlan : workOutPlans){

            workOutPlanDTOS.add(new WorkOutPlanDTO(workOutPlan.getWorkOutPlanId(),workOutPlan.getMemberId(),workOutPlan.getStartDate(),workOutPlan.getEndDate(),workOutPlan.getDescription()));

        }

        return workOutPlanDTOS;

    }

    @Override
    public boolean save(WorkOutPlanDTO workOutPlanDTO) throws SQLException {
        return workOutPlanDAO.save(new WorkOutPlan(workOutPlanDTO.getWorkOutPlanId(),workOutPlanDTO.getMemberId(),workOutPlanDTO.getStartDate(),workOutPlanDTO.getEndDate(),workOutPlanDTO.getDescription()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return workOutPlanDAO.delete(id);
    }

    @Override
    public boolean update(WorkOutPlanDTO workOutPlanDTO) throws SQLException {
        return workOutPlanDAO.update(new WorkOutPlan(workOutPlanDTO.getWorkOutPlanId(),workOutPlanDTO.getMemberId(),workOutPlanDTO.getStartDate(),workOutPlanDTO.getEndDate(),workOutPlanDTO.getDescription()));

    }

    @Override
    public String getMemberNameByWorkOutPlanId(String workOutPlanId) throws SQLException {
        return queryDAO.getMemberNameByWorkOutPlanId(workOutPlanId);
    }
}
