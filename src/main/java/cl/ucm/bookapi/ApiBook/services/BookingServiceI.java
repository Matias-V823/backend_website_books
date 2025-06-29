package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Booking.BookingRequest;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingResponse;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingReturnResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingServiceI {

    BookingResponse createNewBooking(BookingRequest bookingRequest);
    Page<BookingResponse> getBookingByEmail(Pageable pageable, String email);
    BookingReturnResponse createReturnBooking(Long idBooking);

}
