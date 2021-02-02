import {NgModule, Optional, SkipSelf} from '@angular/core';

import { authInterceptorProviders } from './interceptors/auth.interceptor';

import {ClubManagementService} from './services/club-management.service';
import {UserService} from './services/user.service';
import {UserManagementService} from './services/user-management.service';
import {TokenStorageService} from './services/auth/token-storage.service';
import {AuthService} from './services/auth/auth.service';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// Import Calender-Module
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';



@NgModule({
  declarations: [],
  providers: [
    authInterceptorProviders,
    ClubManagementService,
    UserService,
    UserManagementService,
    TokenStorageService,
    AuthService
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgbModule,
    CalendarModule.forRoot({provide: DateAdapter, useFactory: adapterFactory}),
  ],
  exports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    NgbModule,
    CalendarModule
  ]
})
export class CoreModule {

  constructor(@Optional() @SkipSelf() core: CoreModule ){
    if (core) {
      throw new Error('You should import core module only in the root module');
    }
  }

}
