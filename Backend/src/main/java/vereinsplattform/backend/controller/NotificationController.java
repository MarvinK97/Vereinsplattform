package vereinsplattform.backend.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.dto.request.CreateNotificationRequest;
import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.service.NotificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Get all Notification
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Notification> findAllNotifications () { return notificationService.getNotifications(); }

    // Get one Notification
    @GetMapping("{notificationid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Optional<Notification> getNotification (@PathVariable Long notificationid) {
        return notificationService.getNotification(notificationid);
    }

    // Get all Notification from a Club
    @GetMapping("clubs/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Notification> findAllNotifications (@PathVariable Long clubid) {
       return notificationService.getClubNotifications(clubid);
    }

    // Create a new Notification
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Notification createNotificiation (@RequestBody CreateNotificationRequest request) {
        Notification notification = new Notification(request.getMessage(), request.getClubid());
        return notificationService.saveNotification(notification);
    }

    // Delete one Notification
    @DeleteMapping("{notificationid}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteNotification(@PathVariable Long notificationid){
        notificationService.deleteNotification(notificationid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
