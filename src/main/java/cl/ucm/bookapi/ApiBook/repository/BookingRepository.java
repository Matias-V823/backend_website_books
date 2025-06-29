package cl.ucm.bookapi.ApiBook.repository;

import cl.ucm.bookapi.ApiBook.models.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Page<Booking> findByUserEmailOrderByDateBookingDesc(Pageable pageable, String email);
}
