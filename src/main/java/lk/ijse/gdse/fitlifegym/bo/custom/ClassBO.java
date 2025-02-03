package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.AttendanceDTO;
import lk.ijse.gdse.fitlifegym.dto.BookingDTO;
import lk.ijse.gdse.fitlifegym.dto.ClassDTO;
import lk.ijse.gdse.fitlifegym.entity.Cls;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassBO extends SuperBO {

    String generateNewClassId() throws SQLException;

    ArrayList<ClassDTO> getAllClasses() throws SQLException;

    boolean saveClasses(ClassDTO classDTO) throws SQLException;

    boolean deleteClasses(String classId) throws SQLException;

    boolean updateClasses(ClassDTO classDTO) throws SQLException;

    ClassDTO getClassEntityById(String classId) throws SQLException;




}
