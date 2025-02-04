package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.BookingDAO;
import lk.ijse.gdse.fitlifegym.dto.BookingDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

        public String generateId() throws SQLException {
                ResultSet rst = SQLUtil.execute("SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1");

                if (rst.next()){
                      return rst.getString(1);

                }

                return null;
        }

        public ArrayList<Booking> getAll() throws SQLException {

                ResultSet rst = SQLUtil.execute("SELECT * FROM booking");

                ArrayList<Booking> bookings = new ArrayList<>();

                while (rst.next()){
                        Booking booking = new Booking(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getString(3),
                                rst.getString(4)

                        );

                        bookings.add(booking);


                }

                return bookings;
        }


        public boolean update(Booking booking) throws SQLException {

                return SQLUtil.execute("UPDATE  booking SET classId =? , date=? , time = ? WHERE bookingid=?",

                        booking.getClassId(),
                        booking.getDate(),
                        booking.getTime(),
                        booking.getBookingId()

                        );


        }

        public boolean delete(String bookingId) throws SQLException {
                return SQLUtil.execute("DELETE FROM booking WHERE bookingid=?",bookingId);


        }

        public boolean save(Booking booking) throws SQLException {

                return SQLUtil.execute("INSERT INTO booking VALUES(?,?,?,?)",

                        booking.getBookingId(),
                        booking.getClassId(),
                        booking.getDate(),
                        booking.getTime()


                        );

        }


}
