package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.BookingDTO;
import lk.ijse.gdse.fitlifegym.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {

    String generateBookingId() throws SQLException;

    ArrayList<BookingDTO> getAllBookings() throws SQLException;

    boolean saveBooking(BookingDTO bookingDTO) throws SQLException;

    boolean deleteBooking(String bookingId) throws SQLException;

    boolean updateBooking(BookingDTO bookingDTO) throws SQLException;

}
