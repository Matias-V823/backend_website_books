package cl.ucm.bookapi.ApiBook.dto.Book;

import cl.ucm.bookapi.ApiBook.models.Book;

public record BookResponse(
        Integer idBook,
        String title,
        String author,
        String type,
        byte[] image64
) {
    public BookResponse(Book book) {
        this(
                book.getIdBook(),
                book.getTitle(),
                book.getAuthor(),
                book.getType(),
                book.getImage64()
        );
    }
}
