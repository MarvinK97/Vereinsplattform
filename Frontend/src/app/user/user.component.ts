import { Component, OnInit } from '@angular/core';
import {UserService} from "../core/services/user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  enterPageLoader: boolean = true;
  inClub: boolean;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.inClub().subscribe(res => {
      this.inClub = res.status == "200 OK";
      this.enterPageLoader = false;
    })
  }

}
