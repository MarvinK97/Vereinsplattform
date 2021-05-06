import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

import {UserService} from '../../core/services/user.service';
import {UserManagementService} from '../../core/services/user-management.service';

import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {EditUserDialogComponent} from "./edit-user-dialog/edit-user-dialog.component";

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

  // Dialogs
  editUserDialogRef: MatDialogRef<EditUserDialogComponent>;

  constructor(private userService: UserService,
              private userManagementService: UserManagementService,
              private dialogEditUser: MatDialog
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
    this.userManagementService.getAllUsers().subscribe(
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

  openEditUserDialog(row): void {
    const rowOld = row;

    this.editUserDialogRef = this.dialogEditUser.open(EditUserDialogComponent, {
      hasBackdrop: false,
      data: {
        id: row.id,
        email: row.email,
        username: row.username
      }
    });

    this.editUserDialogRef
      .afterClosed()
      .subscribe(res => {
        if (res === 'delete') {
          this.userManagementService.deleteUser(row).subscribe((res) => {
            console.log(res);
            this.getUserList();
          });
        } else {
          if (res) {
            if (res === JSON.stringify(rowOld)) {
              // console.log('No value changed!');
            } else {
              this.userManagementService.updateUser(JSON.parse(res)).subscribe(
                () => {
                  this.getUserList();
                });
            }
          }
        }
      })
  }



}
