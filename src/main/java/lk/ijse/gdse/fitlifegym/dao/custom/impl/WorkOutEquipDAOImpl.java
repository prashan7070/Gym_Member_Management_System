package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.WorkOutEquipDAO;
import lk.ijse.gdse.fitlifegym.dto.WorkOutEquipDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.WorkOutEquip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkOutEquipDAOImpl implements WorkOutEquipDAO {

    @Override
    public String generateId() throws SQLException {
        return "";
    }

    public ArrayList<WorkOutEquip> getAll() throws SQLException {

            ResultSet  rst = SQLUtil.execute("SELECT * FROM workoutequipment");

            ArrayList<WorkOutEquip> workOutEquips = new ArrayList<>();

            while (rst.next()){

                WorkOutEquip workOutEquip = new WorkOutEquip(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)

                );

                workOutEquips.add(workOutEquip);

            }

            return workOutEquips;


        }

        public boolean save(WorkOutEquip workOutEquip) throws SQLException {
            return SQLUtil.execute("INSERT INTO workoutequipment VALUES(?,?,?,?,?)",

                    workOutEquip.getEquipmentId(),
                    workOutEquip.getWorkOutPlanId(),
                    workOutEquip.getUsageFrequency(),
                    workOutEquip.getDurationPerSession(),
                    workOutEquip.getInstructions()

                    );

        }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }

    public boolean update(WorkOutEquip workOutEquip) throws SQLException {
        return SQLUtil.execute("UPDATE workoutequipment SET usageFrequency=? , durationPerSession=? , instructions=?  WHERE equipmentId=? AND workOutPlanId=? ",


                workOutEquip.getUsageFrequency(),
                workOutEquip.getDurationPerSession(),
                workOutEquip.getInstructions(),
                workOutEquip.getEquipmentId(),
                workOutEquip.getWorkOutPlanId()


        );

    }



    public boolean delete(String equipmentId,String workOutPlanId) throws SQLException {
            return SQLUtil.execute("DELETE FROM workoutequipment WHERE equipmentId=? AND workOutPlanId=?",equipmentId,workOutPlanId);

    }




}
