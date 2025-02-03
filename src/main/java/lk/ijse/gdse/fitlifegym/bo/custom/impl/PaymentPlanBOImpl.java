package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.PaymentPlanBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.PaymentPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.PaymentPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentPlanBOImpl implements PaymentPlanBO {


    PaymentPlanDAO paymentPlanDAO = (PaymentPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT_PLAN);

    @Override
    public String generateId() throws SQLException {
        return paymentPlanDAO.generateId();
    }

    @Override
    public ArrayList<PaymentPlanDTO> getAll() throws SQLException {
        ArrayList<PaymentPlan> paymentPlans = paymentPlanDAO.getAll();
        ArrayList<PaymentPlanDTO> paymentPlanDTOS = new ArrayList<>();

        for (PaymentPlan paymentPlan : paymentPlans){
            paymentPlanDTOS.add(new PaymentPlanDTO(paymentPlan.getPlanId(),paymentPlan.getPlanName(),paymentPlan.getDuration(),paymentPlan.getPrice()));
        }

        return paymentPlanDTOS;
    }

    @Override
    public boolean save(PaymentPlanDTO paymentPlanDTO) throws SQLException {
        return paymentPlanDAO.save(new PaymentPlan(paymentPlanDTO.getPlanId(),paymentPlanDTO.getPlanName(),paymentPlanDTO.getDuration(),paymentPlanDTO.getPrice()));
    }

    @Override
    public boolean delete(String paymentPlanId) throws SQLException {
        return paymentPlanDAO.delete(paymentPlanId);
    }

    @Override
    public boolean update(PaymentPlanDTO paymentPlanDTO) throws SQLException {
        return paymentPlanDAO.update(new PaymentPlan(paymentPlanDTO.getPlanId(),paymentPlanDTO.getPlanName(),paymentPlanDTO.getDuration(),paymentPlanDTO.getPrice()));

    }

    @Override
    public PaymentPlanDTO getPaymentPlanEntityById(String planId) throws SQLException {
        PaymentPlan paymentPlan = paymentPlanDAO.getPaymentPlanEntityById(planId);
        return new PaymentPlanDTO(paymentPlan.getPlanId(),paymentPlan.getPlanName(),paymentPlan.getDuration(),paymentPlan.getPrice());
    }
}
