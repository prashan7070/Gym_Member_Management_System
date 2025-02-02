package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.SupplierDAO;
import lk.ijse.gdse.fitlifegym.dto.SupplierDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

        public String generateId() throws SQLException {
            ResultSet rst = SQLUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");

            if (rst.next()){
                String lastId = rst.getString(1);
                String subString = lastId.substring(2);
                int i = Integer.parseInt(subString);
                int newIndex = i+1;
                return String.format("SU%03d",newIndex);
            }

            return "SU001";
        }


        public ArrayList<Supplier> getAll() throws SQLException {

            ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");

            ArrayList<Supplier> suppliers = new ArrayList<>();

            while (rst.next()){

                Supplier supplier = new Supplier(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5)


                );

                suppliers.add(supplier);

            }

            return suppliers;



        }

//        public String getNameBySupplierId(String supplierId) throws SQLException {
//            ResultSet rst = SQLUtil.execute("SELECT name FROM supplier WHERE supplierId = ?",supplierId);
//
//            if (rst.next()){
//                return rst.getString(1);
//            }
//
//            return null;
//
//
//        }

    public Supplier getSupplierEntityBySupplierId(String supplierId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT supplierId , name , description , contactInfo , address FROM supplier WHERE supplierId = ?",supplierId);

        if (rst.next()){
            return new Supplier(rst.getString(1),
                                   rst.getString(2),
                                   rst.getString(3),rst.getString(4),
                                   rst.getString(5));
        }

        return null;


    }





        public boolean update(Supplier supplier) throws SQLException {

            return SQLUtil.execute("UPDATE supplier SET name=? , description=?,contactInfo=? , address=? WHERE supplierId =?",

                    supplier.getName(),
                    supplier.getDescription(),
                    supplier.getContactInfo(),
                    supplier.getAddress(),
                    supplier.getSupplierId()


                    );



        }


    public boolean save(Supplier supplier) throws SQLException {

        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",

                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getDescription(),
                supplier.getContactInfo(),
                supplier.getAddress()

        );



    }


    public boolean delete(String supplierId) throws SQLException {

            return SQLUtil.execute("DELETE FROM supplier WHERE supplierId=?",supplierId);

    }


}
