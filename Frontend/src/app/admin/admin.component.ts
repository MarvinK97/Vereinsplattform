import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})


export class AdminComponent implements OnInit, AfterViewInit {

  title = 'angular-material-tab-router';
  navLinks: any[];
  activeLinkIndex = 0;

  constructor(private router: Router) {
    this.navLinks = [
      {
        label: 'Benutzerverwaltung',
        link: '/admin/user',
        index: 0
      }, {
        label: 'Vereinsverwaltung',
        link: '/admin/club',
        index: 1
      }
    ];
  }

  ngOnInit(): void {
    this.router.events.subscribe((res) => {
      this.activeLinkIndex = this.navLinks.indexOf(this.navLinks.find(tab => tab.link === '.' + this.router.url));
    });
  }

  ngAfterViewInit(): void {
  }

}


