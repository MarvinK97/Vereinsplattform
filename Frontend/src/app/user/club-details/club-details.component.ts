import {Component, OnInit} from '@angular/core';
import {ClubService} from "../../core/services/club.service";

@Component({
  selector: 'app-club-details',
  templateUrl: './club-details.component.html',
  styleUrls: ['./club-details.component.css']
})
export class ClubDetailsComponent implements OnInit {

  club: Club;
  enterPageLoader: boolean = true;

  constructor(private clubService: ClubService) { }

  ngOnInit(): void {
    this.getClub();
  }

  getClub() {
    this.clubService.getClub().subscribe(res => {
      this.club = res;
      this.enterPageLoader = false;
    })
  }

  leaveClub() {
    this.clubService.leaveClub(this.club.id).subscribe( () => {
      window.location.reload();
    });

  }

}

export interface Club {
  id: number;
  name: string;
  street: string;
  zipcode: string;
  city: string;
}
