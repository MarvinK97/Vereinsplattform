import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild,} from '@angular/core';
import {Subject} from 'rxjs';
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
  events: CalendarEvent[] = [];

  // Aktuelles Datum
  viewDate: Date = new Date();

  // Reload Elements
  refresh: Subject<any> = new Subject();

  constructor(private calenderService: CalendarService,
              private dialogAddEvent: MatDialog,
              private clubService: ClubService) {}

  ngOnInit() {

    console.log(JSON.stringify(new Date()));

    // Get Users club
    this.clubService.getClub().subscribe(res => {
      this.club = res;
      this.getEvents();
    })

    this.setView(CalendarView.Week);
  }

  // ---------------------------------------------------
  // Funktionen / Events
  // ---------------------------------------------------

  getEvents(): void {
    // Get Events
    this.calenderService.getCalendarEvents(this.club.id).subscribe(data => {
      data.forEach(element => {

        this.events = [
          ...this.events,
          {
            title: element.title,
            start: new Date(element.start),
            end: new Date(element.end),
            color: this.colors[element.color],
            allDay: element.allDay
          },
        ];

        // this.events.push(element);
        // console.log("Ich bin ein element");
        // console.log(element);
      });
      // this.events = this.events1;
      // this.events = data;
      // console.log("Ich bin Events Array");
      // console.log(this.events);
      // console.log("Ich bin data");
      // console.log(data);
    })

  }

  addEvent(): void {
    this.addEventDialogRef = this.dialogAddEvent.open(AddEventDialogComponent);

    this.addEventDialogRef
      .afterClosed()
      .subscribe(res => {
        console.log(JSON.parse(res));
        console.log(this.club.id);
        this.calenderService.addEvent(JSON.parse(res), this.club.id).subscribe(
          () => {
            console.log(this.club.id);
            this.events = this.eventsEmpty;
            this.getEvents();
          });
      });
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


  colors: any = {
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

  eventsEmpty: CalendarEvent[] = [];


}





