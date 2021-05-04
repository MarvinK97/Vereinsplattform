package vereinsplattform.backend.service;

import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotifications(Long clubId) {
        return notificationRepository.findByClubId(clubId);
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationRepository.getOne(id);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        Notification notificationToUpdate = notificationRepository.getOne(notification.getId());
        notificationToUpdate.setClub(notification.getClub());
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
