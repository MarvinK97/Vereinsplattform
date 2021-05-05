package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.entity.Role;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Newsfeed> findByClubId(Long clubId);

    interface Newsfeed {
        Long getId();
        String getMessage();
        Timestamp getCreatedAt();

    }
}
