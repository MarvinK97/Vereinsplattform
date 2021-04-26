import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ClubManagementComponent} from './club-management.component';
import {SharedModule} from '../../shared/shared.module';

const routes: Routes = [
  { path: '', component: ClubManagementComponent},
];

@NgModule({
  declarations: [],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class ClubManagementModule { }
