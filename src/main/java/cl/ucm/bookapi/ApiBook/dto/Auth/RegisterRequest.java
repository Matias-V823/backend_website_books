package cl.ucm.bookapi.ApiBook.dto.Auth;

public record RegisterRequest(
        String email,
        String name,
        String lastName,
        String password
) {
}
