package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    default List<Notification> getNotifications(){
        return null;
    }

    default List<Notification> getClubNotifications(Long clubId) {return null; }

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
