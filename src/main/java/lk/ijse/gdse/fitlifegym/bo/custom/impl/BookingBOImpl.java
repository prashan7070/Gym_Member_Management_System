package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.BookingBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.BookingDAO;
import lk.ijse.gdse.fitlifegym.dto.BookingDTO;
import lk.ijse.gdse.fitlifegym.entity.Booking;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {

    BookingDAO bookingDAO = (BookingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKING);


    @Override
    public String generateBookingId() throws SQLException {
        return bookingDAO.generateId();
    }

    @Override
    public ArrayList<BookingDTO> getAllBookings() throws SQLException {
        ArrayList<Booking> bookings = bookingDAO.getAll();
        ArrayList<BookingDTO> bookingDTOS = new ArrayList<>();

        for (Booking booking : bookings){
            bookingDTOS.add(new BookingDTO(booking.getBookingId(),booking.getClassId(),booking.getDate(),booking.getTime()));
        }

        return bookingDTOS;
    }

    @Override
    public boolean saveBooking(BookingDTO bookingDTO) throws SQLException {
        return bookingDAO.save(new Booking(bookingDTO.getBookingId(),bookingDTO.getClassId(),bookingDTO.getDate(),bookingDTO.getTime()));
    }

    @Override
    public boolean deleteBooking(String bookingId) throws SQLException {
        return bookingDAO.delete(bookingId);
    }

    @Override
    public boolean updateBooking(BookingDTO bookingDTO) throws SQLException {
        return bookingDAO.update(new Booking(bookingDTO.getBookingId(),bookingDTO.getClassId(),bookingDTO.getDate(),bookingDTO.getTime()));
    }


}
