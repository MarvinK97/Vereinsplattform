import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';
import { AuthGuard } from '../core/guards/auth.guard';



const routes: Routes = [
  { path: '', component: AdminComponent, canActivate: [AuthGuard], children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'user'
      },
      {
        path: 'user',
        loadChildren: () => import('../admin/user-management/user-management.module').then(m => m.UserManagementModule)
      },
      {
        path: 'club',
        loadChildren: () => import('../admin/club-management/club-management.module').then(m => m.ClubManagementModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
