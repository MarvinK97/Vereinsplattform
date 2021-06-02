package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vereinsplattform.backend.entity.CalendarEvent;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository <CalendarEvent, Long> {

    Optional<CalendarEvent> findByStart(String start);

    List<CalendarUserView> findByClubId(long clubId);

    //Projection für Mitgliederverwaltung
    interface CalendarUserView{
        String getStart();
        String getEnd();
        String getTitle();
        String getColor();
        Boolean getAllDay();
    }
}
