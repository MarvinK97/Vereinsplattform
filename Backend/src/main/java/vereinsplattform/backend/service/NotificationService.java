package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    default List<Notification> getNotifications(){
        return null;
    }

    default List<NotificationRepository.Newsfeed> getClubNotifications(Long clubId) {return null; }

    default Optional<Notification> getNotification(Long NotificationId){
        return null;
    }

    default Notification saveNotification(Notification notification) {
        return null;
    }

    default Notification updateNotification(Notification notification) {
        return null;
    }

    default void deleteNotification(Long id) {

    }

}
