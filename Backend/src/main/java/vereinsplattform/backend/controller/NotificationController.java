package vereinsplattform.backend.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.dto.request.CreateNotificationRequest;
import vereinsplattform.backend.entity.Club;
import vereinsplattform.backend.entity.Notification;
import vereinsplattform.backend.repository.ClubRepository;
import vereinsplattform.backend.service.NotificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final ClubRepository clubRepository;

    public NotificationController(NotificationService notificationService, ClubRepository clubRepository) {
        this.notificationService = notificationService;
        this.clubRepository = clubRepository;
    }

    // TODO: only one notification is returned, thats why for get notif from a club, an interface was created
    // Get all Notification
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Notification> findAllNotifications () { return notificationService.getNotifications(); }

    // Get one Notification
    @GetMapping("{notificationid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Notification> getNotification (@PathVariable Long notificationid) {
        return notificationService.getNotifications(notificationid);
    }

    // Get all Notification from a Club
    @GetMapping("clubs/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Notification> findAllNotifications (@PathVariable Long clubid) {
       return notificationService.getNotifications(clubid);
    }

    // Create a new Notification
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Notification createNotificiation (@RequestBody CreateNotificationRequest request) {
        Club club = clubRepository.getOne(request.getClubid());
        System.out.println("1");
        Notification notification = new Notification(request.getMessage(), club);
        System.out.println("2");
        return notificationService.saveNotification(notification);
    }

    @DeleteMapping("{notificationid}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteNotification(@PathVariable Long notificationid){
        notificationService.deleteNotification(notificationid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
