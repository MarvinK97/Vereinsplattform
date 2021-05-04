package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.repository.NotificationRepository;

import java.util.List;

public interface NotificationService {

    default List<Notification> getNotifications(){
        return null;
    }

    default List<Notification> getNotifications(Long clubId) {return null; }

    default Notification getNotification(Long id){
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
