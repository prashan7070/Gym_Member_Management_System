package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.PaymentDAO;
import lk.ijse.gdse.fitlifegym.dto.PaymentDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT paymentId FROM payment ORDER BY paymentId DESC LIMIT 1");

        if (rst.next()){
            return rst.getString(1);

        }

        return null;
    }


    public ArrayList<Payment> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");

        ArrayList<Payment> payments = new ArrayList<>();

        while (rst.next()){

            Payment payment = new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            );

            payments.add(payment);


        }

        return payments;

    }

    public boolean save(Payment payment) throws SQLException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?,?,?,?,?,?,?,?)",
                payment.getPaymentId(),
                payment.getMemberId(),
                payment.getPlanId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getDate(),
                payment.getEndDate(),
                payment.getStatus()


        );
    }

    public boolean delete(String paymentId) throws SQLException {
        return SQLUtil.execute("DELETE FROM payment WHERE paymentId=?",paymentId);
    }


    public boolean update(Payment payment) throws SQLException {
        return SQLUtil.execute("UPDATE payment SET memberId = ? , planId = ? , amount = ? , paymentMethod = ? , date = ? ,endDate = ? , status =? WHERE paymentId = ?" ,

                payment.getMemberId(),
                payment.getPlanId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getDate(),
                payment.getEndDate(),
                payment.getStatus(),
                payment.getPaymentId()


        );

    }





}
