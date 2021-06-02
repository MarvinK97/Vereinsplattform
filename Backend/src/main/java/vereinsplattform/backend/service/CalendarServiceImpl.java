package vereinsplattform.backend.service;

import org.springframework.stereotype.Service;
import vereinsplattform.backend.entity.CalendarEvent;
import vereinsplattform.backend.repository.CalendarRepository;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public List<CalendarRepository.CalendarUserView> getCalendarEvent(long clubId) {
        return this.calendarRepository.findByClubId(clubId);
    }

    @Override
    public CalendarEvent saveCalendarEvent(CalendarEvent calendarEvent) {
        return calendarRepository.save(calendarEvent);
    }

    // TODO: fix functionality
    @Override
    public void deleteCalendarEvent(long clubId) {
        CalendarEvent event = calendarRepository.getOne(clubId);
        calendarRepository.delete(event);
    }
}
