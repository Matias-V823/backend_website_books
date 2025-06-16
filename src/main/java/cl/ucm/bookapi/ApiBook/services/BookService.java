package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.Booking;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import cl.ucm.bookapi.ApiBook.repository.BookRepository;
import cl.ucm.bookapi.ApiBook.repository.CopyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookService implements BookServiceI{
    private final BookRepository bookRepository;
    private final CopyBookRepository copyBookRepository;

    @Autowired
    public BookService(
            BookRepository bookRepository,
            CopyBookRepository copyBookRepository
    ){
        this.bookRepository = bookRepository;
        this.copyBookRepository = copyBookRepository;
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBookByType(Pageable pageable, String type) {
        return bookRepository.findByType(pageable, type);
    }

    @Override
    public Page<Book> findBookByTitle(Pageable pageable, String title) {
        return bookRepository.findByTitleContainingIgnoreCase(pageable, title);
    }

    @Override
    public Book newBook(BookRequest book) {
        return bookRepository.save(new Book(book));
    }

    @Override
    public CopyBook newCopyBook(Long idBook) {
        Optional<Book> book = bookRepository.findById(idBook);
        if (!book.isPresent()) {
            return null;
        }
        return copyBookRepository.save(new CopyBook(book.get()));
    }

    @Override
    public Booking bookReserve(String title) {
        return null;
    }
}
