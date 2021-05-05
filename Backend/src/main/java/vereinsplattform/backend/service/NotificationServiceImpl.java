package vereinsplattform.backend.service;

import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // All Notifications
    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    // All Notifications of one club
    @Override
    public List<NotificationRepository.Newsfeed> getClubNotifications(Long clubId) {
        return notificationRepository.findByClubId(clubId);
    }

    //One Notification
    @Override
    public Optional<Notification> getNotification(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        Notification notificationToUpdate = notificationRepository.getOne(notification.getId());
        notificationToUpdate.setClubId(notification.getClubId());
        notificationToUpdate.setMessage(notification.getMessage());
        notificationToUpdate.setEditedAt(notification.getEditedAt());
        return notificationRepository.save(notificationToUpdate);
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.getOne(id);
        notificationRepository.delete(notification);
    }
}
