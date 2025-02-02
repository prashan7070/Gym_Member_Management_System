package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.WorkOutPlanDAO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutPlanDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.WorkOutPlan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkOutPlanDAOImpl implements WorkOutPlanDAO {

            public String generateId() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT workOutPlanId FROM workoutplan ORDER BY workOutPlanId DESC LIMIT 1");

//                if (rst.next()){
//
//                    String lastId = rst.getString(1);
//                    String subString = lastId.substring(1);
//                    int i = Integer.parseInt(subString);
//                    int newIndex = i+1;
//                    return String.format("W%03d",newIndex);
//
//
//                }
//
//                return "W001";

                return rst.next() ? rst.getString(1) : null;

            }


            public ArrayList<WorkOutPlan> getAll() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT * FROM workoutplan");
                ArrayList<WorkOutPlan> workOutPlans = new ArrayList<>();

                        while(rst.next()){
                                WorkOutPlan workOutPlan = new WorkOutPlan(
                                        rst.getString(1),
                                        rst.getString(2),
                                        rst.getString(3),
                                        rst.getString(4),
                                        rst.getString(5)

                                );

                                workOutPlans.add(workOutPlan);


                        }

                        return workOutPlans;


            }


            public boolean delete (String workOutPlanId) throws SQLException {

                return SQLUtil.execute("DELETE FROM workoutplan WHERE workOutPlanId =?",workOutPlanId);

            }

            public boolean update(WorkOutPlan workOutPlan) throws SQLException {
                return SQLUtil.execute("UPDATE workoutplan SET memberId=? , startDate =? , endDate=? , description=? WHERE workOutPlanId=?",
                        workOutPlan.getMemberId(),
                        workOutPlan.getStartDate(),
                        workOutPlan.getEndDate(),
                        workOutPlan.getDescription(),
                        workOutPlan.getWorkOutPlanId()

                        );


            }


            public boolean save(WorkOutPlan workOutPlan) throws SQLException {
                return SQLUtil.execute("INSERT INTO workoutplan VALUES(?,?,?,?,?)",

                        workOutPlan.getWorkOutPlanId(),
                        workOutPlan.getMemberId(),
                        workOutPlan.getStartDate(),
                        workOutPlan.getEndDate(),
                        workOutPlan.getDescription()
                        );

            }


//            public String getMemberNameByWorkOutPlanId(String workOutPlanId) throws SQLException {
//                ResultSet rst = SQLUtil.execute("SELECT m.name FROM member m JOIN workoutplan w ON m.memberId=w.memberId WHERE w.workoutplanId=?",workOutPlanId);
//
//                if (rst.next()){
//                    return rst.getString("name");
//
//                }
//
//                return null;
//            }



}
