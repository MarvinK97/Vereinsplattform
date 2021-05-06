import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {AdminRoutingModule} from './admin-routing.module';

import {ClubManagementComponent} from './club-management/club-management.component';
import {UserManagementComponent} from './user-management/user-management.component';
import {EditClubDialogComponent} from './club-management/edit-club-dialog/edit-club-dialog.component';
import {AddClubDialogComponent} from './club-management/add-club-dialog/add-club-dialog.component';
import {AdminComponent} from './admin.component';
import {AdminMaterialModule} from "./admin-material.module";
import {EditUserDialogComponent} from "./user-management/edit-user-dialog/edit-user-dialog.component";


@NgModule({
  declarations: [
    AdminComponent,
    ClubManagementComponent,
    UserManagementComponent,
    EditClubDialogComponent,
    AddClubDialogComponent,
    EditUserDialogComponent
  ],
  imports: [
    AdminRoutingModule,
    SharedModule,
    AdminMaterialModule
  ]
})
export class AdminModule { }
