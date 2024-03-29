import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CalendarEvent} from "angular-calendar";
import {Observable} from "rxjs";
import {addDays, addHours, startOfDay, subDays} from "date-fns";

const API_URL = 'http://localhost:8080/api/calendars/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class CalendarService {

  constructor(private http: HttpClient) { }

  getCalendarEvents(id: number): Observable<any> {
    return this.http.get<CalendarEvent[]>(API_URL + 'clubs/' + id);
  }

  addEvent(event: CalendarEvent, id: number): Observable<any> {
    console.log('ClubId:' + id);
    return this.http.post(API_URL + '', {
      start: event.start,
      end: event.end,
      title: event.title,
      color: event.color,
      allDay: event.allDay,
      clubId: id
    }, httpOptions);
  }

  deleteEvent(event: CalendarEvent, id: number): Observable<any> {
    return this.http.delete(API_URL + 'clubs' + id)
  }

  events: CalendarEvent[] = [
    {
      start: subDays(startOfDay(new Date()), 1),
      end: addDays(new Date(), 1),
      title: 'A 3 day event',
      color: colors.red,
      allDay: true
    },
    {
      start: subDays(startOfDay(new Date()), 2),
      end: addHours(subDays(startOfDay(new Date()), 2), 1),
      title: 'A 3 day event',
      color: colors.blue,
      allDay: false
    },
    {
      start: subDays(startOfDay(new Date()), 3),
      end: addHours(subDays(startOfDay(new Date()), 3), 2),
      title: 'A 3 day event',
      color: colors.red,
      allDay: false
    }
  ];

}

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};


