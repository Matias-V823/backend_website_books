package cl.ucm.bookapi.ApiBook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "fine")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFine;
    private Integer amount;
    private String description;
    private Boolean state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User userFk;

    public Fine(Booking booking, User user){
        long daysLate = ChronoUnit.DAYS.between(
                booking.getDateReturn().toLocalDate(), LocalDate.now()
        );

        this.amount = Math.toIntExact(
                daysLate * 1000
        );
        this.description = "Multa asociada al libro "
                + booking.getCopybookFk().getBook().getTitle().toLowerCase()
                + " del autor "
                + booking.getCopybookFk().getBook().getAuthor().toLowerCase()
                + " con un retraso de "
                + daysLate
                + " dias.";
        this.state = Boolean.TRUE;
        this.userFk = user;
    }

}
