package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.AttendanceDAO;
import lk.ijse.gdse.fitlifegym.dto.AttendanceDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {

        public String generateId() throws SQLException {

                    ResultSet rst = SQLUtil.execute("SELECT attendanceId FROM classdetails ORDER BY attendanceId DESC LIMIT 1");

                    if (rst.next()){

                        return rst.getString(1);


                    }

                    return null;


        }

        public ArrayList<Attendance> getAll() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT * FROM classdetails");

                ArrayList<Attendance> attendances = new ArrayList<>();

                while (rst.next()){

                        Attendance attendance = new Attendance(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getString(3),
                                rst.getString(4),
                                rst.getString(5)


                        );

                        attendances.add(attendance);

                }

                return attendances;

        }



        public boolean save(Attendance attendance) throws SQLException {

            return SQLUtil.execute("INSERT INTO classdetails VALUES(?,?,?,?,?)",
                    attendance.getAttendanceId(),
                    attendance.getClassId(),
                    attendance.getMemberId(),
                    attendance.getDate(),
                    attendance.getStatus()

                    );


        }




        public boolean delete(String attendaceId) throws SQLException {

            return SQLUtil.execute("DELETE FROM classdetails WHERE attendanceId = ?",attendaceId);

        }



        public boolean update(Attendance attendance) throws SQLException {

            return SQLUtil.execute("UPDATE classdetails SET classId=? , memberId=? , date=? , status=?  WHERE attendanceId = ? ",
                    attendance.getClassId(),
                    attendance.getMemberId(),
                    attendance.getDate(),
                    attendance.getStatus(),
                    attendance.getAttendanceId()

                    );

        }



}
