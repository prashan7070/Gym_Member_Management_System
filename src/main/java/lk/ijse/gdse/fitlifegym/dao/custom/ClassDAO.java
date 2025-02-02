package lk.ijse.gdse.fitlifegym.dao.custom;

import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.entity.Cls;

import java.sql.SQLException;

public interface ClassDAO extends CrudDAO<Cls> {

     Cls getClassEntityById(String classId) throws SQLException;



}
