package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.EquipmentDAO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentDTO;
import lk.ijse.gdse.fitlifegym.dto.EquipmentSupplyDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Equipment;
import lk.ijse.gdse.fitlifegym.entity.EquipmentSupply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentDAOImpl implements EquipmentDAO {

        public ArrayList<Equipment> getAll() throws SQLException {


            ResultSet rst = SQLUtil.execute("SELECT * FROM equipment");

            ArrayList<Equipment> equipments = new ArrayList<>();

            while (rst.next()){
                Equipment equipment = new Equipment(

                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getString(5)

                );

                equipments.add(equipment);

            }

            return equipments;


        }


        public Equipment getEquipmentEntityById(String equipmentId) throws SQLException {
            ResultSet rst = SQLUtil.execute("SELECT equipmentId , name , description , quantity ,status FROM equipment WHERE equipmentId=?",equipmentId);

            if (rst.next()){
                return new Equipment(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getString(5)

                );
            }

            return null;

        }


        public String generateId() throws SQLException {
            ResultSet rst = SQLUtil.execute("SELECT equipmentId FROM equipment ORDER BY equipmentId DESC LIMIT 1");

            if (rst.next()){
                String lastId = rst.getString(1);
                return lastId;

            }



            return null;
        }


    public boolean save(Equipment currentEquipment) throws SQLException {

            return SQLUtil.execute("INSERT INTO equipment VALUES(?,?,?,?,?)",

                    currentEquipment.getEquipmentId(),
                    currentEquipment.getName(),
                    currentEquipment.getDescription(),
                    currentEquipment.getQuantity(),
                    currentEquipment.getStatus()

                    );


    }



    public boolean isExistsEquipment(Equipment currentEquipment) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM equipment WHERE equipmentId=?", currentEquipment.getEquipmentId());
        if (rst.next()) {
            return rst.getInt(1) > 0;
        }
        return false;
    }




    public boolean isStatusUnavailable(EquipmentSupply equipmentSupply) throws SQLException {

            ResultSet rst = SQLUtil.execute("SELECT status FROM equipment WHERE equipmentId=?",equipmentSupply.getEquipmentId());

            if (rst.next()){
                return "Unavailable".equalsIgnoreCase(rst.getString(1));

            }

            return false;


    }

    public boolean updateUsingEquipSupply(EquipmentSupply equipmentSupply) throws SQLException {

        return SQLUtil.execute("UPDATE equipment SET status=? WHERE equipmentId=?",

                "Available",
                equipmentSupply.getEquipmentId()

        );


    }

    public boolean isUpdateQtyInEquipment(EquipmentSupply equipmentSupply) throws SQLException {

        return  SQLUtil.execute("UPDATE equipment SET quantity = quantity + ? WHERE equipmentId=?",

                    equipmentSupply.getQuantityOrdered(),
                    equipmentSupply.getEquipmentId()

                );


    }

    public boolean delete(String equipmentId) throws SQLException {
            return SQLUtil.execute("DELETE FROM equipment WHERE equipmentId=?",equipmentId);


    }

    @Override
    public boolean update(Equipment equipment) throws SQLException {
        return false;
    }
}
