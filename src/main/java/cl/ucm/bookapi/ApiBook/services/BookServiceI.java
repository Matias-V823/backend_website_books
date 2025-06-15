package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.dto.Book.BookResponse;
import cl.ucm.bookapi.ApiBook.dto.Booking.BookingResponse;
import cl.ucm.bookapi.ApiBook.dto.CopyBook.CopyBookResponse;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.Booking;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookServiceI {

    Page<BookResponse> getAllBooks(Pageable pageable);
    Page<BookResponse> getBookByType(Pageable pageable, String type);
    Book newBook(BookRequest book);
    Book findBookByTitle(String title);
    CopyBook newCopyBook(Integer idBook);
    Booking bookReserve(String title);

}
