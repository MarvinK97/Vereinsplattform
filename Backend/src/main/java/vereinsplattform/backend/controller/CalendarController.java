package vereinsplattform.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vereinsplattform.backend.entity.CalendarEvent;
import vereinsplattform.backend.repository.CalendarRepository;
import vereinsplattform.backend.service.CalendarService;
import vereinsplattform.backend.service.ClubService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

    private final ClubService clubService;
    private final CalendarService calendarService;

    public CalendarController(ClubService clubService, CalendarService calendarService) {
        this.clubService = clubService;
        this.calendarService = calendarService;
    }

    // Get all events from club
    @GetMapping("clubs/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<CalendarRepository.CalendarUserView> findAllEventsFromClub (@PathVariable Long clubid){
        return this.calendarService.getCalendarEvent(clubid);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CalendarEvent createEvent (@RequestBody CalendarEvent event){
        System.out.println(event);
        return calendarService.saveCalendarEvent(event);
    }

    // Delete existing club
    @DeleteMapping("clubs/{clubid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable Long clubid){
        calendarService.deleteCalendarEvent(clubid);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
