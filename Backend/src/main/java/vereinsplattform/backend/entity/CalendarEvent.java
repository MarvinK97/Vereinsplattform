package vereinsplattform.backend.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class CalendarEvent {

    String start;
    String end;
    String title;
    String color;
    Boolean allDay;
    long clubId;

    public CalendarEvent(String start, String end, String title, String color, Boolean allDay, long clubId) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.color = color;
        this.allDay = allDay;
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

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }
}
