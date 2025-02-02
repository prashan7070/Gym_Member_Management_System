package lk.ijse.gdse.fitlifegym.dao.custom.impl;


import lk.ijse.gdse.fitlifegym.dao.custom.DietPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.DietPlanDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.DietPlan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanDAOImpl implements DietPlanDAO {

            public String generateId() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT dietPlanId FROM dietplan ORDER BY dietPlanId DESC LIMIT 1");

//                if (rst.next()){
//                    String lastId = rst.getString(1);
//                    String subString = lastId.substring(1);
//                    int i = Integer.parseInt(subString);
//                    int newIndex = i+1;
//                    return String.format("D%03d",newIndex);
//
//                }
//
//                return "D001";

                return rst.next() ? rst.getString(1) : null;


            }


            public ArrayList<DietPlan> getAll() throws SQLException {
                ResultSet rst = SQLUtil.execute("SELECT * FROM dietplan");
                ArrayList<DietPlan> dietPlans = new ArrayList<>();

                while (rst.next()){
                    DietPlan dietPlan = new DietPlan(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)

                    );

                    dietPlans.add(dietPlan);
                }

                return dietPlans;

            }


            public boolean delete(String dietPlanId) throws SQLException {

                return  SQLUtil.execute("DELETE FROM dietplan WHERE dietPlanId=?",dietPlanId);


            }

            public boolean save(DietPlan dietPlan) throws SQLException {


                return SQLUtil.execute("INSERT INTO dietplan VALUES(?,?,?,?,?)",
                        dietPlan.getDietPlanId(),
                        dietPlan.getMemberId(),
                        dietPlan.getStartDate(),
                        dietPlan.getEndDate(),
                        dietPlan.getDescription()

                        );


            }


            public boolean update(DietPlan dietPlan) throws SQLException {

                return SQLUtil.execute("UPDATE dietplan SET memberId=? , startDate=? , endDate=? , description=? WHERE diePlanId=?",

                        dietPlan.getMemberId(),
                        dietPlan.getStartDate(),
                        dietPlan.getEndDate(),
                        dietPlan.getDescription(),
                        dietPlan.getDietPlanId()


                        );

            }


}
