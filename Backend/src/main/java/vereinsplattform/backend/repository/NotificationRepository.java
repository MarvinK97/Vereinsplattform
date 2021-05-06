package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vereinsplattform.backend.entity.Notification;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Newsfeed> findByClubId(Long clubId);

    interface Newsfeed {
        Long getId();
        String getMessage();
        Timestamp getCreatedAt();

    }
}
