import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "../shared/shared.module";
import {UserMaterialModule} from "./user-material.module";

import {UserComponent} from "./user.component";
import {SearchClubComponent} from './search-club/search-club.component';
import {ClubDetailsComponent} from "./club-details/club-details.component";
import {AuthGuard} from "../core/guards/auth.guard";


const routes: Routes = [
  { path: '', component: UserComponent, canActivate: [AuthGuard]}
];

@NgModule({
  declarations: [
    SearchClubComponent,
    UserComponent,
    ClubDetailsComponent
  ],
  exports: [
  ],
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
    UserMaterialModule
  ]
})
export class UserModule { }
