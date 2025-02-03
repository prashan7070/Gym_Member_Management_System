package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {

    String generateNewAttendanceId() throws SQLException;

    ArrayList<AttendanceDTO> getAllAttendance() throws SQLException;

    boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException;

    boolean deleteAttendance(String attendanceId) throws SQLException;

    boolean updateAttendance(AttendanceDTO attendanceDTO) throws SQLException;


}
