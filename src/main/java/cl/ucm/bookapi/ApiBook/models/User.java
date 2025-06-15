package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user_library")
@Entity(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email;
    private String name;
    private String lastName;
    private String password;
    private Boolean state;
}
