package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Booking.BookingRequest;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingResponse;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingReturnResponse;
import cl.ucm.bookapi.ApiBook.services.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/new")
    public ResponseEntity<BookingResponse> createNewBooking(
            @RequestBody BookingRequest bookingRequest
    ) {
        BookingResponse bookingResponse = bookingService.createNewBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }

    @GetMapping("/find/{email}")
    public  ResponseEntity<Page<BookingResponse>> getBookingByEmail(
            @PathVariable String email,
            Pageable pageable
    ) {
        return ResponseEntity.ok(bookingService.getBookingByEmail(pageable, email));
    }

    @PostMapping("/return/{idBooking}")
    public ResponseEntity<BookingReturnResponse> returnBooking(
            @PathVariable Long idBooking
    ) {
        return ResponseEntity.ok(bookingService.createReturnBooking(idBooking));
    }

}
