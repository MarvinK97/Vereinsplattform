import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../shared/shared.module';

import {CalendarComponent} from './calendar.component';
import {CalendarCommonModule, CalendarDayModule, CalendarMonthModule, CalendarWeekModule} from 'angular-calendar';
import { AddEventDialogComponent } from './add-event-dialog/add-event-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatInputModule} from "@angular/material/input";
import {MatTabsModule} from "@angular/material/tabs";
import {MatButtonModule} from "@angular/material/button";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatCardModule} from "@angular/material/card";
import {MatDatepickerModule} from "@angular/material/datepicker";

import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from '@angular-material-components/datetime-picker';

const routes: Routes = [
  { path: '', component: CalendarComponent}
];

@NgModule({
  declarations: [
    CalendarComponent,
    AddEventDialogComponent
  ],
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
    CalendarCommonModule,
    CalendarMonthModule,
    CalendarWeekModule,
    CalendarDayModule,
    MatDialogModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatTabsModule,
    MatDialogModule,
    MatButtonModule,
    MatOptionModule,
    MatSelectModule,
    MatCardModule,

    MatDatepickerModule,
    MatInputModule,

  ]
})
export class CalendarModule { }
