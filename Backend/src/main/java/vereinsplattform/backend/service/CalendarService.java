package vereinsplattform.backend.service;

import vereinsplattform.backend.entity.CalendarEvent;
import vereinsplattform.backend.repository.CalendarRepository;

import java.util.List;

public interface CalendarService {

    default List<CalendarRepository.CalendarUserView> getCalendarEvent(long clubId){
        return null;
    }

    default CalendarEvent saveCalendarEvent(CalendarEvent calendarEvent) {
        return null;
    }

    default void deleteCalendarEvent(long clubId) {

    }
}
