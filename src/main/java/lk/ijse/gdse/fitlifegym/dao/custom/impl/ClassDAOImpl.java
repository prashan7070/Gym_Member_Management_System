package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.ClassDAO;
import lk.ijse.gdse.fitlifegym.dto.ClassDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Cls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAOImpl implements ClassDAO {

    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT classId FROM classes ORDER BY classId DESC LIMIT 1");

        if (rst.next()){

            String lastId = rst.getString(1);
            String subString = lastId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("C%03d",newIndex);


        }

        return "C001";

    }


    public ArrayList<Cls> getAll() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM classes");

        ArrayList<Cls> classes = new ArrayList<>();

        while (rst.next()){
            Cls cls = new Cls(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

            classes.add(cls);


        }

        return classes;


    }

    public boolean save(Cls cls) throws SQLException {
        return SQLUtil.execute("INSERT INTO classes VALUES(?,?,?,?)",

                cls.getClassId(),
                cls.getEmployeeId(),
                cls.getClassName(),
                cls.getClassType()

                );
    }


    public boolean delete(String classId) throws SQLException {

        return SQLUtil.execute("DELETE FROM classes WHERE classId=?",classId);

    }

    public boolean update(Cls cls) throws SQLException {
        return SQLUtil.execute("UPDATE classes SET employeeId = ? , className = ? , classType = ? WHERE classId = ?",

                cls.getEmployeeId(),
                cls.getClassName(),
                cls.getClassType(),
                cls.getClassId()

                );



    }

    public Cls getClassEntityById(String classId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT classId , employeeId , className , classType FROM classes WHERE classId = ?",classId);

        if (rst.next()){
            return new Cls(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );


        }

        return null;
    }


}
