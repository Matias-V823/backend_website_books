package cl.ucm.bookapi.ApiBook.models;

import cl.ucm.bookapi.ApiBook.dto.Auth.RegisterRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "user_library")
@Entity(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private String email;
    private String name;
    private String lastName;
    private String password;
    private Boolean state;
    @OneToMany(mappedBy = "userFk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRol> userRol;

    public User(RegisterRequest registerRequest) {
        this.email = registerRequest.email();
        this.name = registerRequest.name();
        this.lastName = registerRequest.lastName();
        this.password = registerRequest.password();
        this.state = Boolean.TRUE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRol.stream().map(
                ul -> new SimpleGrantedAuthority("ROLE_" + ul.getRolFk().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.state != null && this.state;
    }
}
