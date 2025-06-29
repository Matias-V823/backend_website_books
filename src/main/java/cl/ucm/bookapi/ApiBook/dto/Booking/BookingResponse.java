package cl.ucm.bookapi.ApiBook.dto.Booking;

import cl.ucm.bookapi.ApiBook.dto.CopyBook.CopyBookResponse;
import cl.ucm.bookapi.ApiBook.models.Booking;

import java.time.LocalDateTime;

public record BookingResponse(
    Long idBooking,
    CopyBookResponse copyBook,
    String lector,
    LocalDateTime dateBooking,
    LocalDateTime dateReturn,
    Boolean state
) {
    public BookingResponse(Booking booking){
        this(
                booking.getIdBooking(),
                new CopyBookResponse(booking.getCopybookFk()),
                booking.getUser().getEmail(),
                booking.getDateBooking(),
                booking.getDateReturn(),
                booking.getState()
        );
    }
}
