package cl.ucm.bookapi.ApiBook.models;

import cl.ucm.bookapi.ApiBook.dto.Booking.BookingRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copybook_fk")
    private CopyBook copybookFk;
    private LocalDateTime dateBooking;
    private LocalDateTime dateReturn;
    private Boolean state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    public Booking(User lector, CopyBook copyBook) {
        this.copybookFk = copyBook;
        this.dateBooking = LocalDateTime.now();
        this.dateReturn = LocalDateTime.now().plusDays(5);
        this.state = Boolean.TRUE;
        this.user = lector;
    }
}
