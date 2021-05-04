package vereinsplattform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vereinsplattform.backend.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository <Notification, Long> {

    List<Notification> findByClubId (Long clubId);

}
