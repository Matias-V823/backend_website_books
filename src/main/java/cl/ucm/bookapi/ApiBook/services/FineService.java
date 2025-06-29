package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Fine.FineResponse;
import cl.ucm.bookapi.ApiBook.models.Fine;
import cl.ucm.bookapi.ApiBook.models.User;
import cl.ucm.bookapi.ApiBook.repository.FineRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class FineService implements FineServiceI {
    private final UserRepository userRepository;
    private final FineRepository fineRepository;

    public FineService(
            UserRepository userRepository,
            FineRepository fineRepository
    ) {
        this.userRepository = userRepository;
        this.fineRepository = fineRepository;
    }

    @Override
    public Page<FineResponse> getFinesByEmail(Pageable pageable, String email) {
        Optional<User> user = userRepository.findById(email);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se logro encontrar al usuario");
        }

        return fineRepository.findByUserFkEmailOrderByIdFineDesc(pageable, email).map(FineResponse::new);
    }

    @Override
    public FineResponse fineStateChange(Long idFine) {
        Optional<Fine> fine = fineRepository.findById(idFine);

        if (fine.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se logro encontrar la multa");
        }

        Fine f = fine.get();
        f.setState(Boolean.FALSE);
        fineRepository.save(f);

        return new FineResponse(f);
    }
}
