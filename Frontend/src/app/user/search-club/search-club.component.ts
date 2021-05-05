import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {ClubManagementService} from "../../core/services/club-management.service";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {ClubService} from "../../core/services/club.service";


@Component({
  selector: 'app-search-club',
  templateUrl: './search-club.component.html',
  styleUrls: ['./search-club.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class SearchClubComponent implements OnInit, AfterViewInit {

  activeRequest: string | null = null;
  clubs: string | undefined;
  columnsToDisplayClub = ['name', 'street', 'plz', 'ort'];
  expandedElement: Club | null;

  dataSourceClub = new MatTableDataSource<Club>();
  @ViewChild('TableClubPaginator', {static: true, read: MatPaginator}) paginatorClub: MatPaginator;
  @ViewChild('TableClubSort') sortClub: MatSort;

  constructor(private clubManagementService: ClubManagementService,
              private clubService: ClubService) { }

  ngOnInit(): void {
    this.getClubList();
    this.getActiveRequest();
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

  joinClub(club: Club): void {
    this.clubService.joinClub(club.id).subscribe(
      data => {
        console.log(JSON.stringify(data));
        window.location.reload();
      }
    );
  }

  getActiveRequest(): void {
    this.clubManagementService.getActiveRequest().subscribe(
      data => {
        if (data != null) {
          this.activeRequest = data;
        }
      }
    )
  }

  deleteActiveRequest(): void {
    console.log("111111111111111")
    this.clubManagementService.deleteActiveRequest().subscribe(
      data => {
        console.log(JSON.stringify(data));
        window.location.reload();
      }
    )
  }

}

export interface Club {
  id: number;
  name: string;
  street: string;
  zipcode: string;
  city: string;
}

