package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.PaymentPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentPlanBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<PaymentPlanDTO> getAll() throws SQLException;

    boolean save(PaymentPlanDTO paymentPlanDTO) throws SQLException;

    boolean delete(String paymentPlanId) throws SQLException;

    boolean update(PaymentPlanDTO paymentPlanDTO) throws SQLException;

    PaymentPlanDTO getPaymentPlanEntityById(String planId) throws SQLException;

}
