package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.PaymentBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.MemberDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.PaymentDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.PaymentPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentDTO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

     PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public String generateId() throws SQLException {
        String id = paymentDAO.generateId();

        if (id!=null){
            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("P%03d",newIndex);
        }

        return "P001";
    }

    @Override
    public ArrayList<PaymentDTO> getAll() throws SQLException {
        ArrayList<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments){
            paymentDTOS.add(new PaymentDTO(payment.getPaymentId(),payment.getMemberId(),payment.getPlanId(),payment.getAmount(),payment.getPaymentMethod(),payment.getDate(),payment.getEndDate(),payment.getStatus()));
        }

        return paymentDTOS;
    }


    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException {
        return paymentDAO.save(new Payment(paymentDTO.getPaymentId(),paymentDTO.getMemberId(),paymentDTO.getPlanId(),paymentDTO.getAmount(),paymentDTO.getPaymentMethod(),paymentDTO.getDate(),paymentDTO.getEndDate(),paymentDTO.getStatus()));
    }

    @Override
    public boolean delete(String paymentId) throws SQLException {
        return paymentDAO.delete(paymentId);
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException {
        return paymentDAO.update(new Payment(paymentDTO.getPaymentId(),paymentDTO.getMemberId(),paymentDTO.getPlanId(),paymentDTO.getAmount(),paymentDTO.getPaymentMethod(),paymentDTO.getDate(),paymentDTO.getEndDate(),paymentDTO.getStatus()));

    }

}
