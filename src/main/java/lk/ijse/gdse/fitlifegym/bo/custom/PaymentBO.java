package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.Member;
import lk.ijse.gdse.fitlifegym.entity.PaymentPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<PaymentDTO> getAll() throws SQLException;

    boolean save(PaymentDTO paymentDTO) throws SQLException;

    boolean delete(String paymentId) throws SQLException;

    boolean update(PaymentDTO paymentDTO) throws SQLException;




}
