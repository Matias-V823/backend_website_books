package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import cl.ucm.bookapi.ApiBook.dto.Book.BookResponse;
import cl.ucm.bookapi.ApiBook.dto.CopyBook.CopyBookResponse;
import cl.ucm.bookapi.ApiBook.models.Book;
import cl.ucm.bookapi.ApiBook.models.CopyBook;
import cl.ucm.bookapi.ApiBook.services.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController  {
    private final BookService bookService;

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

    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<BookResponse> newBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("type") String type,
            @RequestParam("image64") MultipartFile image
    ) throws IOException {
        BookRequest bookRequest = new BookRequest(
                title,
                author,
                type,
                image.getBytes()
        );

        Book book = bookService.newBook(bookRequest);
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

    @GetMapping("/copy/{title}")
    @Transactional
    ResponseEntity<Page<CopyBookResponse>> getCopies(
            @PathVariable String title,
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                bookService.copyBookByTitle(pageable, title).map(CopyBookResponse::new)
        );
    }

}
