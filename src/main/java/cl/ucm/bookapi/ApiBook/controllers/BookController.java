package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.dto.Book.BookResponse;
import cl.ucm.bookapi.ApiBook.dto.CopyBook.CopyBookResponse;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import cl.ucm.bookapi.ApiBook.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/book")
public class BookController  {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/all")
    ResponseEntity<Page<BookResponse>> getAllBooks(Pageable pageable){
        return ResponseEntity
                .ok(bookService.getAllBooks(pageable)
                        .map(BookResponse::new));
    }

    @GetMapping("/all/{type}")
    ResponseEntity<Page<BookResponse>> getAllBooksByType(
            @PathVariable String type,
            Pageable pageable
    ){
        return ResponseEntity
                .ok(bookService.getBookByType(pageable, type)
                        .map(BookResponse::new));
    }

    @GetMapping("/find/{title}")
    ResponseEntity<Page<BookResponse>> findBookByTitle(
            @PathVariable String title,
            Pageable pageable
    ){
        return ResponseEntity.ok(bookService.findBookByTitle(pageable, title).map(BookResponse::new));
    }

    @PostMapping("/new")
    ResponseEntity<BookResponse> newBook(
            @RequestBody @Valid BookRequest bookBody
    ) {
        Book book = bookService.newBook(bookBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BookResponse(book));
    }

    @PostMapping("/newcopy/{idBook}")
    ResponseEntity<CopyBookResponse> newCopyBook(
            @PathVariable Long idBook
    ) {
        CopyBook newCopyBook = bookService.newCopyBook(idBook);
        if (newCopyBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CopyBookResponse(newCopyBook));
    }

}
