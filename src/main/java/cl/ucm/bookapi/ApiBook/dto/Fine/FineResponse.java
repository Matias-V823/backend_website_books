package cl.ucm.bookapi.ApiBook.dto.Fine;

import cl.ucm.bookapi.ApiBook.models.Fine;

public record FineResponse(
    Long idFine,
    Integer amount,
    String description,
    Boolean state
) {
    public FineResponse(
            Fine fine
    ) {
        this(
                fine.getIdFine(),
                fine.getAmount(),
                fine.getDescription(),
                fine.getState()
        );
    }
}
