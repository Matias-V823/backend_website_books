package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByType(Pageable pageable, String type);
    Page<Book> findByTitleContainingIgnoreCase(Pageable pageable, String title);
}
