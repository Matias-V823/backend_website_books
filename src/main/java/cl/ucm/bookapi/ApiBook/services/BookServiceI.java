package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookServiceI {

    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> getBookByType(Pageable pageable, String type);
    Page<Book> findBookByTitle(Pageable pageable, String title);
    Book newBook(BookRequest book);
    CopyBook newCopyBook(Long idBook);
    Page<CopyBook> copyBookByTitle(Pageable pageable, String title);

}
