package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "copy_book")
@Data
public class CopyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCopybook;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_fk")
    private Integer BookFk;
    private Boolean state;
}
