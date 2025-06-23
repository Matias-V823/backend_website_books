package cl.ucm.bookapi.ApiBook.dto.Auth;

import cl.ucm.bookapi.ApiBook.models.User;

import java.util.List;

public record LoginResponse(
        String email,
        String name,
        String lastName,
        Boolean state,
        String token,
        List<String> roles
) {
    public LoginResponse(User user, String token) {
        this(
            user.getEmail(),
            user.getName(),
            user.getLastName(),
            user.getState(),
            token,
            user.getUserRol().stream().map(ur -> ur.getRolFk().getName()).toList()
        );
    }
}
