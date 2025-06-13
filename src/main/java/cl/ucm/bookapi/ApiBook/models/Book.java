package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBook;
    private String author;
    private String title;
    private String type;
    @Lob
    @Column(name = "image64", columnDefinition = "LONGBLOB")
    private byte[] image64;
}
