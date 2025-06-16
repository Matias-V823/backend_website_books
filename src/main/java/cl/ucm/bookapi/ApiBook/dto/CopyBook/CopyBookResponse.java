package cl.ucm.bookapi.ApiBook.dto.CopyBook;

import cl.ucm.bookapi.ApiBook.dto.Book.BookResponse;
import cl.ucm.bookapi.ApiBook.models.CopyBook;

public record CopyBookResponse(
        Long idCopyBook,
        Boolean state,
        BookResponse book
) {
    public CopyBookResponse(CopyBook copyBook) {
        this(
                copyBook.getIdCopybook(),
                copyBook.getState(),
                new BookResponse(copyBook.getBook())
        );

    }
}
