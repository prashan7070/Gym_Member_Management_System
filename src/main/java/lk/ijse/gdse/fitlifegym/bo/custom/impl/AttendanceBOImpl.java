package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.AttendanceBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.AttendanceDAO;
import lk.ijse.gdse.fitlifegym.dto.AttendanceDTO;
import lk.ijse.gdse.fitlifegym.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ATTENDANCE);


    @Override
    public String generateNewAttendanceId() throws SQLException {
        String id = attendanceDAO.generateId();

        if (id!=null){

            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("A%03d",newIndex);

        }

        return "A001";
    }

    @Override
    public ArrayList<AttendanceDTO> getAllAttendance() throws SQLException {
        ArrayList<Attendance> attendances = attendanceDAO.getAll();
        ArrayList<AttendanceDTO> attendanceDTOS = new ArrayList<>();

        for (Attendance attendance : attendances){
            attendanceDTOS.add(new AttendanceDTO(attendance.getAttendanceId(),attendance.getClassId(),attendance.getMemberId(),attendance.getDate(),attendance.getStatus()));
        }

        return attendanceDTOS;
    }

    @Override
    public boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException {
        return attendanceDAO.save(new Attendance(attendanceDTO.getAttendanceId(),attendanceDTO.getClassId(),attendanceDTO.getMemberId(),attendanceDTO.getDate(),attendanceDTO.getStatus()));
    }

    @Override
    public boolean deleteAttendance(String attendanceId) throws SQLException {
        return attendanceDAO.delete(attendanceId);
    }

    @Override
    public boolean updateAttendance(AttendanceDTO attendanceDTO) throws SQLException {
        return attendanceDAO.update(new Attendance(attendanceDTO.getAttendanceId(),attendanceDTO.getClassId(),attendanceDTO.getMemberId(),attendanceDTO.getDate(),attendanceDTO.getStatus()));

    }



}
