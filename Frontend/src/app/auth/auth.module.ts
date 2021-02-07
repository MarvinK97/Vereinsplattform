import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { SharedModule } from '../shared/shared.module';

import { AuthComponent } from './auth.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';

import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";


const routes: Routes = [
  { path: '', component: AuthComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
];

@NgModule({
  declarations: [
    AuthComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent
  ],
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
    MatCardModule,
    MatIconModule,
    MatFormFieldModule
  ]
})
export class AuthModule { }
