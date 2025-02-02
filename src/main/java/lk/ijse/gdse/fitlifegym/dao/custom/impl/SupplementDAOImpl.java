package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.SupplementDAO;
import lk.ijse.gdse.fitlifegym.dto.SupplementDTO;
import lk.ijse.gdse.fitlifegym.dto.SupplementSupplyDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Supplement;
import lk.ijse.gdse.fitlifegym.entity.SupplementSupply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementDAOImpl implements SupplementDAO {


    public ArrayList<Supplement> getAll() throws SQLException {


        ResultSet rst = SQLUtil.execute("SELECT * FROM supplement");

        ArrayList<Supplement> supplements = new ArrayList<>();

        while (rst.next()){
            Supplement supplement = new Supplement(

                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)

            );

            supplements.add(supplement);

        }

        return supplements;


    }


    public Supplement getSupplementDtoById(String supplementId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT supplementId , name , dosage , price ,quantity,status FROM supplement WHERE supplementId=?",supplementId);

        if (rst.next()){
            return new Supplement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5),
                    rst.getString(6)

            );
        }

        return null;

    }


    public String generateId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT supplementId FROM supplement ORDER BY supplementId DESC LIMIT 1");

        if (rst.next()){
            String lastId = rst.getString(1);
            String subString = lastId.substring(2);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("SM%03d",newIndex);
        }

        return "SM001";
    }


    public boolean save(Supplement currentSupplement) throws SQLException {

        return SQLUtil.execute("INSERT INTO supplement VALUES(?,?,?,?,?,?)",

                currentSupplement.getSupplementId(),
                currentSupplement.getName(),
                currentSupplement.getDosage(),
                currentSupplement.getPrice(),
                currentSupplement.getQty(),
                currentSupplement.getStatus()


        );


    }

    @Override
    public boolean delete(String T) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Supplement supplement) throws SQLException {
        return false;
    }


    public boolean isExistsSupplement(Supplement currentSupplement) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM supplement WHERE supplementId=?", currentSupplement.getSupplementId());
        if (rst.next()) {
            return rst.getInt(1) > 0;
        }
        return false;
    }




    public boolean isStatusUnavailable(SupplementSupply supplementSupply) throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT status FROM supplement WHERE supplementId=?",supplementSupply.getSupplementId());

        if (rst.next()){
            return "Unavailable".equalsIgnoreCase(rst.getString(1));

        }

        return false;


    }

    public boolean updateSupplementDetailsTable(SupplementSupply supplementSupply) throws SQLException {

        return SQLUtil.execute("UPDATE supplement SET status=? WHERE supplementId=?",

                "Available",
                supplementSupply.getSupplementId()

        );


    }

    public boolean isUpdateQtyInSupplement(SupplementSupply supplementSupply) throws SQLException {

        return  SQLUtil.execute("UPDATE supplement SET quantity = quantity + ? WHERE supplementId=?",

                supplementSupply.getQtyOrdered(),
                supplementSupply.getQtyOrdered()

        );


    }

//    public boolean deleteEquipment(String equipmentId) throws SQLException {
//        return SQLUtil.execute("DELETE FROM equipment WHERE equipmentId=?",equipmentId);
//
//
//    }


}
