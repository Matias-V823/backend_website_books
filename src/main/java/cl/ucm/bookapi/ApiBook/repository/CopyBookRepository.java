package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.models.CopyBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyBookRepository extends JpaRepository<CopyBook, Long> {
}
