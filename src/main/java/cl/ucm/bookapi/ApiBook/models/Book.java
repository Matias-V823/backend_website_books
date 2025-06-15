package cl.ucm.bookapi.ApiBook.models;

import cl.ucm.bookapi.ApiBook.dto.Book.BookRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBook;
    private String author;
    private String title;
    private String type;
    @Lob
    private byte[] image64;

    public Book(BookRequest bookRequest) {
        this.title = bookRequest.title();
        this.author = bookRequest.author();
        this.type = bookRequest.type();
        this.image64 = bookRequest.image64();
    }
}
