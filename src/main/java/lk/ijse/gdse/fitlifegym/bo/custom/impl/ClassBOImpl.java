package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.ClassBO;
import lk.ijse.gdse.fitlifegym.dao.CrudDAO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.AttendanceDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.BookingDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.ClassDAO;
import lk.ijse.gdse.fitlifegym.dto.AttendanceDTO;
import lk.ijse.gdse.fitlifegym.dto.BookingDTO;
import lk.ijse.gdse.fitlifegym.dto.ClassDTO;
import lk.ijse.gdse.fitlifegym.entity.Attendance;
import lk.ijse.gdse.fitlifegym.entity.Booking;
import lk.ijse.gdse.fitlifegym.entity.Cls;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassBOImpl implements ClassBO {

    ClassDAO classDAO = (ClassDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CLASS);

    @Override
    public String generateNewClassId() throws SQLException {
        return classDAO.generateId();
    }

    @Override
    public ArrayList<ClassDTO> getAllClasses() throws SQLException {
        ArrayList<ClassDTO> classDTOS = new ArrayList<>();
        ArrayList<Cls> classes = classDAO.getAll();
        for (Cls cls : classes){
            classDTOS.add(new ClassDTO(cls.getClassId(),cls.getEmployeeId(),cls.getClassName(),cls.getClassType()));
        }

        return classDTOS;
    }

    @Override
    public boolean saveClasses(ClassDTO classDTO) throws SQLException {
        return classDAO.save(new Cls(classDTO.getClassId(),classDTO.getEmployeeId(),classDTO.getClassName(),classDTO.getClassType()));
    }

    @Override
    public boolean deleteClasses(String classId) throws SQLException {
        return classDAO.delete(classId);
    }

    @Override
    public boolean updateClasses(ClassDTO classDTO) throws SQLException {
        return classDAO.update(new Cls(classDTO.getClassId(),classDTO.getEmployeeId(),classDTO.getClassName(),classDTO.getClassType()));
    }

    @Override
    public ClassDTO getClassEntityById(String classId) throws SQLException {
        Cls cls = classDAO.getClassEntityById(classId);
        return new ClassDTO(cls.getClassId(),cls.getEmployeeId(),cls.getClassName(),cls.getClassType());
    }


}
