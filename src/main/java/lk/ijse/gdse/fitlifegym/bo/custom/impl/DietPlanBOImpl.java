package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.DietPlanBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.DietPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.DietPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.DietPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanBOImpl implements DietPlanBO {

    DietPlanDAO dietPlanDAO = (DietPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DIET_PLAN);

    @Override
    public String generateId() throws SQLException {
        String id = dietPlanDAO.generateId();

        if (id!=null){
            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("D%03d",newIndex);
        }

        return "D001";

    }

    @Override
    public ArrayList<DietPlanDTO> getAll() throws SQLException {
        ArrayList<DietPlan> dietPlans = dietPlanDAO.getAll();
        ArrayList<DietPlanDTO> dietPlanDTOS = new ArrayList<>();

        for (DietPlan dietPlan : dietPlans){
            dietPlanDTOS.add(new DietPlanDTO(dietPlan.getDietPlanId(),dietPlan.getMemberId(),dietPlan.getStartDate(),dietPlan.getEndDate(),dietPlan.getDescription()));
        }

        return dietPlanDTOS;
    }

    @Override
    public boolean save(DietPlanDTO dietPlanDTO) throws SQLException {
        return dietPlanDAO.save(new DietPlan(dietPlanDTO.getDietPlanId(),dietPlanDTO.getMemberId(),dietPlanDTO.getStartDate(),dietPlanDTO.getEndDate(),dietPlanDTO.getDescription()));
    }

    @Override
    public boolean delete(String dietPlanId) throws SQLException {
        return dietPlanDAO.delete(dietPlanId);
    }

    @Override
    public boolean update(DietPlanDTO dietPlanDTO) throws SQLException {
        return dietPlanDAO.update(new DietPlan(dietPlanDTO.getDietPlanId(),dietPlanDTO.getMemberId(),dietPlanDTO.getStartDate(),dietPlanDTO.getEndDate(),dietPlanDTO.getDescription()));

    }
}
