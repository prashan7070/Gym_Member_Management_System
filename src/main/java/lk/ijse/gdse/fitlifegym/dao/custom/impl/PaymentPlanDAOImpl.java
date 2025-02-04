package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.PaymentPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.PaymentPlanDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.PaymentPlan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentPlanDAOImpl implements PaymentPlanDAO {

            public ArrayList<PaymentPlan> getAll() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT * FROM paymentplan");

                ArrayList<PaymentPlan> paymentPlans = new ArrayList<>();

                while (rst.next()){
                    PaymentPlan paymentPlan = new PaymentPlan(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDouble(4)

                    );

                    paymentPlans.add(paymentPlan);


                }

                return paymentPlans;

            }


            public String generateId() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT planId FROM paymentplan ORDER BY planId DESC LIMIT 1");

                if (rst.next()){

                    return rst.getString(1);


                }

                return null;

            }


            public PaymentPlan getPaymentPlanEntityById(String planId) throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT planId , planName , planDuration_days , planCost FROM paymentplan WHERE planId = ?",planId);

                if (rst.next()){
                    return new PaymentPlan(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getDouble(4)

                    );


                }

                return null;

            }


    public boolean save(PaymentPlan paymentPlan) throws SQLException {

                return SQLUtil.execute("INSERT INTO paymentplan VALUES(?,?,?,?)",

                        paymentPlan.getPlanId(),
                        paymentPlan.getPlanName(),
                        paymentPlan.getDuration(),
                        paymentPlan.getPrice()

                        );

    }


    public boolean update(PaymentPlan paymentPlan) throws SQLException {

                return SQLUtil.execute("UPDATE paymentplan SET planName=? , planDuration_days=? , planCost = ? WHERE planId = ? ",
                        paymentPlan.getPlanName(),
                        paymentPlan.getDuration(),
                        paymentPlan.getPrice(),
                        paymentPlan.getPlanId()

                        );


    }

    public boolean delete(String planId) throws SQLException {

                return SQLUtil.execute("DELETE FROM paymentplan WHERE planId=?",planId);
    }
}
