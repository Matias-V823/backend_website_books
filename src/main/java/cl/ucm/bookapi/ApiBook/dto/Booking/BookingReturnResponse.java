package cl.ucm.bookapi.ApiBook.dto.Booking;

import cl.ucm.bookapi.ApiBook.dto.CopyBook.CopyBookResponse;
import cl.ucm.bookapi.ApiBook.dto.Fine.FineResponse;
import cl.ucm.bookapi.ApiBook.models.Booking;
import cl.ucm.bookapi.ApiBook.models.Fine;

import java.time.LocalDateTime;

public record BookingReturnResponse(
        Long idBooking,
        CopyBookResponse copyBook,
        String lector,
        LocalDateTime dateBooking,
        LocalDateTime dateReturn,
        Boolean state,
        FineResponse fine
) {
    public BookingReturnResponse(
        Booking booking,
        Fine fine
    ) {
        this(
                booking.getIdBooking(),
                new CopyBookResponse(booking.getCopybookFk()),
                booking.getUser().getEmail(),
                booking.getDateBooking(),
                booking.getDateReturn(),
                booking.getState(),
                fine != null ? new FineResponse(fine): null
        );
    }
}
