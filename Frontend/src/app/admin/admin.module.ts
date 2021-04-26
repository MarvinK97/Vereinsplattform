import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {AdminRoutingModule} from './admin-routing.module';

import {ClubManagementComponent} from './club-management/club-management.component';
import {UserManagementComponent} from './user-management/user-management.component';
import {EditClubDialogComponent} from './club-management/edit-club-dialog/edit-club-dialog.component';
import {AddClubDialogComponent} from './club-management/add-club-dialog/add-club-dialog.component';
import {AdminComponent} from './admin.component';
import {AdminMaterialModule} from "./admin-material.module";


@NgModule({
  declarations: [
    AdminComponent,
    ClubManagementComponent,
    UserManagementComponent,
    EditClubDialogComponent,
    AddClubDialogComponent
  ],
  imports: [
    AdminRoutingModule,
    SharedModule,
    AdminMaterialModule
  ]
})
export class AdminModule { }
