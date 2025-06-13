package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFine;
    private Integer amount;
    private String description;
    private Boolean state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User userFk;

}
