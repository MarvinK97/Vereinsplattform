import {ChangeDetectionStrategy, Component, TemplateRef, ViewChild,} from '@angular/core';
import {addDays, addHours, endOfDay, endOfMonth, startOfDay, subDays,} from 'date-fns';
import {Subject} from 'rxjs';
import {CalendarEvent, CalendarView,} from 'angular-calendar';

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

@Component({
  selector: 'app-calendar',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar.component.css'],
  templateUrl: './calendar.component.html',
})
export class CalendarComponent {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any> | undefined;

  // Standard Ansicht festlegen
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  activeDayIsOpen = true;

  // Events,welche angezeigt werden
  events: CalendarEvent[] = [
    {
      start: subDays(startOfDay(new Date()), 1),
      end: addDays(new Date(), 1),
      title: 'A 3 day event',
      color: colors.red,
      allDay: true,
      resizable: {
        beforeStart: true,
        afterEnd: true,
      },
      draggable: true,
    },
    {
      start: startOfDay(new Date()),
      title: 'An event with no end date',
      color: colors.yellow,
    },
    {
      start: subDays(endOfMonth(new Date()), 3),
      end: addDays(endOfMonth(new Date()), 3),
      title: 'A long event that spans 2 months',
      color: colors.blue,
      allDay: true,
    },
    {
      start: addHours(startOfDay(new Date()), 2),
      end: addHours(new Date(), 2),
      title: 'A draggable and resizable event',
      color: colors.yellow,
      resizable: {
        beforeStart: true,
        afterEnd: true,
      },
      draggable: true,
    },
  ];

  // Aktuelles Datum
  viewDate: Date = new Date();

  // Reload Elements
  refresh: Subject<any> = new Subject();

  constructor() {}

  // ---------------------------------------------------
  // Funktionen / Events
  // ---------------------------------------------------
  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  // tslint:disable-next-line:typedef
  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  // tslint:disable-next-line:typedef
  setView(view: CalendarView) {
    this.view = view;
  }

  // tslint:disable-next-line:typedef
  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}
