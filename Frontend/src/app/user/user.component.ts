import {Component, OnInit} from '@angular/core';
import {ClubService} from "../core/services/club.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  enterPageLoader: boolean = true;
  inClub: boolean;

  constructor(private clubService: ClubService) { }

  ngOnInit(): void {
    this.clubService.getClub().subscribe(res => {
      this.inClub = res != null;
      this.enterPageLoader = false;
    })
  }

}
