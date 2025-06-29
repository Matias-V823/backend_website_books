package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.models.Fine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine, Long> {
    Page<Fine> findByUserFkEmailOrderByIdFineDesc(Pageable pageable, String email);
}
