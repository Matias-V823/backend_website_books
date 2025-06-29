package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Page<User> findByEmailContainingIgnoreCase(Pageable pageable, String email);
}
