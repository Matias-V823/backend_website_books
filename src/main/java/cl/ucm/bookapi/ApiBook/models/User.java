package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user")
@Entity(name = "User")
@Data
public class User {
    @Id
    private String email;
    private String name;
    private String lastName;
    private String password;
    private Boolean state;
}
