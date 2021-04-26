import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {RouterModule, Routes} from '@angular/router';


import {UserManagementComponent} from './user-management.component';

const routes: Routes = [
  { path: '', component: UserManagementComponent},
];

@NgModule({
  declarations: [],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class UserManagementModule { }
