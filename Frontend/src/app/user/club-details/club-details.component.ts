import {Component, OnInit} from '@angular/core';
import {ClubService} from "../../core/services/club.service";
import {NewsfeedService} from "../../core/services/newsfeed.service";

@Component({
  selector: 'app-club-details',
  templateUrl: './club-details.component.html',
  styleUrls: ['./club-details.component.css']
})
export class ClubDetailsComponent implements OnInit {

  club: Club;
  news: News[];
  enterPageLoader: boolean = true;

  constructor(private clubService: ClubService, private newsfeedService: NewsfeedService) { }

  ngOnInit(): void {
    this.getClub();
  }

  getClub() {
    this.clubService.getClub().subscribe(res => {
      this.club = res;
      this.getNewsfeed(this.club.id);
    })
  }

  getNewsfeed(clubid: number) {
    this.newsfeedService.getNewsfeed(clubid).subscribe( data => {
      this.news = data;
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

export interface News {
  message: string;
  id: number;
  createdAt: string;
}
