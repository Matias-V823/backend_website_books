package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "rol")
@Entity(name = "Rol")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
