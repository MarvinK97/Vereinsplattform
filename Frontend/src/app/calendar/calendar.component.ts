
import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild,} from '@angular/core';
import {addDays, addHours, endOfDay, endOfMonth, startOfDay, subDays,} from 'date-fns';
import {Observable, Subject} from 'rxjs';
import {CalendarEvent, CalendarView,} from 'angular-calendar';
import {CalendarService} from "../core/services/calendar.service";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";

import {AddEventDialogComponent} from "./add-event-dialog/add-event-dialog.component";
import {Club} from "../user/club-details/club-details.component";
import {ClubService} from "../core/services/club.service";

@Component({
  selector: 'app-calendar',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./calendar.component.css'],
  templateUrl: './calendar.component.html',
})
export class CalendarComponent implements OnInit {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any> | undefined;

  club: Club;

  // Dialogs
  addEventDialogRef: MatDialogRef<AddEventDialogComponent>;

  // Standard Ansicht festlegen
  view: CalendarView = CalendarView.Week;
  CalendarView = CalendarView;
  activeDayIsOpen = true;

  // Events, welche angezeigt werden
  events: CalendarEvent[];

  // Aktuelles Datum
  viewDate: Date = new Date();

  // Reload Elements
  refresh: Subject<any> = new Subject();

  constructor(private calenderService: CalendarService,
              private dialogAddEvent: MatDialog,
              private clubService: ClubService) {}

  ngOnInit() {
    // Get Users club
    this.clubService.getClub().subscribe(res => {
      this.club = res;
      // Get Events
      this.calenderService.getCalendarEvents(this.club.id).subscribe(data => {
        this.events = data;
      })
    })
  }

  // ---------------------------------------------------
  // Funktionen / Events
  // ---------------------------------------------------

  addEvent(): void {
    this.addEventDialogRef = this.dialogAddEvent.open(AddEventDialogComponent);

    this.addEventDialogRef
      .afterClosed()
      .subscribe(res => {
        //console.log(JSON.parse(res));
        this.calenderService.addEvent(JSON.parse(res), this.club.id).subscribe(
          () => {
            this.calenderService.getCalendarEvents(this.club.id);
          });
      });
  }

/*
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
  } */

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
