package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Reader.ReaderResponse;
import cl.ucm.bookapi.ApiBook.models.User;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ReaderService implements ReaderServiceI{
    private final UserRepository userRepository;

    public ReaderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<ReaderResponse> getReadersByEmail(Pageable pageable, String email) {
        return userRepository.findByEmailContainingIgnoreCase(
                pageable, email
        ).map(ReaderResponse::new);
    }

    @Override
    public ReaderResponse stateChangeReader(String email) {
        Optional<User> reader = userRepository.findById(email);

        if (reader.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se logro encontrar al lector");
        }

        Boolean state = reader.get().getState();
        reader.get().setState(!state);

        return new ReaderResponse(reader.get());
    }
}
