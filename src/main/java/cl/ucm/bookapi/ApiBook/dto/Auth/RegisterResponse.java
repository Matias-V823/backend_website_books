package cl.ucm.bookapi.ApiBook.dto.Auth;

import cl.ucm.bookapi.ApiBook.models.User;

import java.util.List;

public record RegisterResponse(
        String email,
        String name,
        String lastName,
        Boolean state,
        List<String> roles
) {
    public RegisterResponse(User user) {
        this(
                user.getEmail(),
                user.getName(),
                user.getLastName(),
                user.getState(),
                user.getUserRol().stream().map(ur -> ur.getRolFk().getName()).toList()
        );
    }
}
