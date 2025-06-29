package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Booking.BookingRequest;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingResponse;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingReturnResponse;
import cl.ucm.bookapi.ApiBook.models.Booking;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import cl.ucm.bookapi.ApiBook.models.Fine;
import cl.ucm.bookapi.ApiBook.models.User;
import cl.ucm.bookapi.ApiBook.repository.BookingRepository;
import cl.ucm.bookapi.ApiBook.repository.CopyBookRepository;
import cl.ucm.bookapi.ApiBook.repository.FineRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceI {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final CopyBookRepository copyBookRepository;
    private final FineRepository fineRepository;

    public BookingService(
            UserRepository userRepository,
            BookingRepository bookingRepository,
            CopyBookRepository copyBookRepository,
            FineRepository fineRepository
    ){
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.copyBookRepository = copyBookRepository;
        this.fineRepository = fineRepository;
    }

    @Override
    public BookingResponse createNewBooking(BookingRequest bookingRequest) {
        Optional<CopyBook> copyBook = copyBookRepository.findById(bookingRequest.idCopyBook());
        Optional<User> lector = userRepository.findById(bookingRequest.email());

        if (copyBook.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Copia de libro no encontrada");
        }

        if (!copyBook.get().getState()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La copia del libro no está disponible");
        }

        if (lector.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        if (!lector.get().getState()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El lector está bloqueado o sancionado");
        }

        BookingResponse bookingResponse = new BookingResponse(
                bookingRepository.save(new Booking(lector.get(), copyBook.get()))
        );

        copyBook.get().setState(Boolean.FALSE);

        return bookingResponse;
    }

    @Override
    public Page<BookingResponse> getBookingByEmail(Pageable pageable, String email) {
        return bookingRepository
                .findByUserEmailOrderByDateBookingDesc(
                        pageable, email
                ).map(BookingResponse::new);
    }

    @Override
    public BookingReturnResponse createReturnBooking(Long idBooking) {
        Optional<Booking> booking = bookingRepository.findById(idBooking);

        if (booking.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada.");
        }

        booking.get().setState(Boolean.FALSE);
        bookingRepository.save(booking.get());

        if (booking.get().getDateReturn().toLocalDate().isAfter(LocalDate.now())){
            return new BookingReturnResponse(booking.get(), null);
        }

        User user = booking.get().getUser();
        user.setState(Boolean.FALSE);
        userRepository.save(user);

        return new BookingReturnResponse(booking.get(), fineRepository.save(
                new Fine(
                        booking.get(), user
                )
            )
        );
    }
}
