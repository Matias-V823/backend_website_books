package cl.ucm.bookapi.ApiBook.dto.Reader;

import cl.ucm.bookapi.ApiBook.models.User;

public record ReaderResponse(
    String email,
    String name,
    String lastName,
    Boolean state
) {
    public ReaderResponse(User reader) {
        this(
                reader.getEmail(),
                reader.getName(),
                reader.getLastName(),
                reader.getState()
        );
    }
}
