import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';

import {ClubManagementService} from '../../core/services/club-management.service';
import {AddClubDialogComponent} from './add-club-dialog/add-club-dialog.component';
import {EditClubDialogComponent} from './edit-club-dialog/edit-club-dialog.component';

export interface Club {
  id: number;
  name: string;
  street: string;
  zipcode: string;
  city: string;
}

@Component({
  selector: 'app-club-management',
  templateUrl: './club-management.component.html',
  styleUrls: ['./club-management.component.css']
})
export class ClubManagementComponent implements OnInit, AfterViewInit {

  clubs: string | undefined;
  columnsToDisplayClub = ['id', 'name', 'street', 'plz', 'ort'];

  dataSourceClub = new MatTableDataSource<Club>();
  @ViewChild('TableClubPaginator', {static: true, read: MatPaginator}) paginatorClub: MatPaginator;
  @ViewChild('TableClubSort') sortClub: MatSort;

  // Dialogs
  addClubDialogRef: MatDialogRef<AddClubDialogComponent>;
  editClubDialogRef: MatDialogRef<EditClubDialogComponent>;

  constructor(private clubManagementService: ClubManagementService,
              private dialogAddClub: MatDialog,
              private dialogEditClub: MatDialog) { }

  ngOnInit(): void {
    this.getClubList();
  }

  ngAfterViewInit(): void {
    this.dataSourceClub.paginator = this.paginatorClub;
    this.dataSourceClub.sort = this.sortClub;
  }

  applyFilterClub(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceClub.filter = filterValue.trim().toLowerCase();

    if (this.dataSourceClub.paginator) {
      this.dataSourceClub.paginator.firstPage();
    }
  }

  openAddClubDialog(): void {
    this.addClubDialogRef = this.dialogAddClub.open(AddClubDialogComponent);

    this.addClubDialogRef
      .afterClosed()
      .subscribe(res => {
        // console.log(JSON.parse(res));
        this.clubManagementService.createClub(JSON.parse(res)).subscribe(
          () => {
            this.getClubList();
          });
      });
  }

  openEditClubDialog(row): void {
    const rowOld = row;

    this.editClubDialogRef = this.dialogEditClub.open(EditClubDialogComponent, {
      hasBackdrop: false,
      data: {
        id: row.id,
        name: row.name,
        street: row.street,
        zipcode: row.zipcode,
        city: row.city
      }
    });
    // on close of dialog check if changes are made and post them
    this.editClubDialogRef
      .afterClosed()
      .subscribe(res => {
        if (res === 'delete') {
          this.clubManagementService.deleteClub(row).subscribe((res) => {
            console.log(res);
            this.getClubList();
          });
        } else {
          if (res) {
            if (res === JSON.stringify(rowOld)) {
              // console.log('No value changed!');
            } else {
              this.clubManagementService.updateClub(JSON.parse(res)).subscribe(
                () => {
                  this.getClubList();
                });
            }
          }
        }
      });
  }

  getClubList(): void {
    this.clubManagementService.getAllClubs().subscribe(
      data => {
        this.clubs = JSON.parse(data);
        this.dataSourceClub.data = JSON.parse(data);
        this.dataSourceClub.paginator = this.paginatorClub;
      },
      err => {
        this.clubs = JSON.parse(err.error).message;
      }
    );
  }

}
