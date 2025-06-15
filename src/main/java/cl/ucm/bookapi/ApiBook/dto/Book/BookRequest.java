package cl.ucm.bookapi.ApiBook.dto.Book;

import jakarta.persistence.Lob;

public record BookRequest(
        String title,
        String author,
        String type,
        byte[] image64
) {
}
