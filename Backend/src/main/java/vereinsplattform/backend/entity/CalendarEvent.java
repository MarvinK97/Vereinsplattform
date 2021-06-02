package vereinsplattform.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "calendarEvents",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "eventId")
        })
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long eventId;
    long clubId;
    String start;
    String end;
    String title;
    String color;
    Boolean allDay;

    public CalendarEvent(long clubId, String start, String end, String title, String color, Boolean allDay) {
        this.clubId = clubId;
        this.start = start;
        this.end = end;
        this.title = title;
        this.color = color;
        this.allDay = allDay;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "eventId=" + eventId +
                ", clubId=" + clubId +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", allDay=" + allDay +
                '}';
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }
}
