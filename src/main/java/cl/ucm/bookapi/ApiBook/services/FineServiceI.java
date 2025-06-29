package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Fine.FineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FineServiceI {

    Page<FineResponse> getFinesByEmail(Pageable pageable, String email);
    FineResponse fineStateChange(Long idFine);
}
