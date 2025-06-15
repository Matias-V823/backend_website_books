package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.dto.Book.BookResponse;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.Booking;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import cl.ucm.bookapi.ApiBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookService implements BookServiceI{
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(BookResponse::new);
    }

    @Override
    public Page<BookResponse> getBookByType(Pageable pageable, String type) {
        return null;
    }

    @Override
    public Book newBook(BookRequest book) {
        return bookRepository.save(new Book(book));
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public CopyBook newCopyBook(Integer idBook) {
        return null;
    }

    @Override
    public Booking bookReserve(String title) {
        return null;
    }
}
