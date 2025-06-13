package cl.ucm.bookapi.ApiBook.models;


import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user_role")
@Entity(name = "UsesRol")
@Data
public class UsesRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRolUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rolFk;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "email")
    private User userFk;


}
