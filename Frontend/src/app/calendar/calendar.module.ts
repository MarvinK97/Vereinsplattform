import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

import {CalendarComponent} from './calendar.component';
import {CalendarCommonModule, CalendarDayModule, CalendarMonthModule, CalendarWeekModule } from 'angular-calendar';


const routes: Routes = [
  { path: '', component: CalendarComponent}
];

@NgModule({
  declarations: [
    CalendarComponent
  ],
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
    CalendarCommonModule,
    CalendarMonthModule,
    CalendarWeekModule,
    CalendarDayModule
  ]
})
export class CalendarModule { }
