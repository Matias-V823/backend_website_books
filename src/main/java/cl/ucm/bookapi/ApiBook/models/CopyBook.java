package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "copy_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCopybook;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_fk")
    private Book book;
    private Boolean state;

    public CopyBook(
            Book book
    ){
        this.book = book;
        this.state = Boolean.TRUE;
    }
}
