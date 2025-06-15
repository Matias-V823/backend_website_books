package cl.ucm.bookapi.ApiBook.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_role")
@Entity(name = "UsesRol")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
