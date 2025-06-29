package cl.ucm.bookapi.ApiBook.dto.Booking;

import java.time.LocalDateTime;

public record BookingRequest(
        String email,
        Long idCopyBook
) {
}
