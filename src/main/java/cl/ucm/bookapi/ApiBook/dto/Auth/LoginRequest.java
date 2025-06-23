package cl.ucm.bookapi.ApiBook.dto.Auth;

public record LoginRequest(
        String username,
        String password
) {
}
