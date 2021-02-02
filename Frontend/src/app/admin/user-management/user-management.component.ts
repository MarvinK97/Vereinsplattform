import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

import {UserService} from '../../core/services/user.service';
import {UserManagementService} from '../../core/services/user-management.service';

import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';

export interface User {
  username: string;
  id: number;
  email: string;
}

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit, AfterViewInit {

  // User-Management
  users: string | undefined;
  columnsToDisplay = ['id', 'email', 'username'];

  dataSource = new MatTableDataSource<User>();
  @ViewChild('TableUserPaginator', {static: true, read: MatPaginator}) paginator: MatPaginator;
  @ViewChild('TableUserSort') sort: MatSort;


  constructor(private userService: UserService,
              private userManagementService: UserManagementService,
              ) { }

  ngOnInit(): void {
    // Get UserList
    this.getUserList();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  getUserList(): void {
    this.userManagementService.getAllUsersExtended().subscribe(
      data => {
        this.users = JSON.parse(data);
        this.dataSource.data = JSON.parse(data);
        this.dataSource.paginator = this.paginator;
      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onRowClicked(row): void {
    alert('clicked me!');
    console.log('Row clicked: ', row);
  }

}
