package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Reader.ReaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReaderServiceI {

    Page<ReaderResponse> getReadersByEmail(Pageable pageable, String email);
    ReaderResponse stateChangeReader(String email);
}
