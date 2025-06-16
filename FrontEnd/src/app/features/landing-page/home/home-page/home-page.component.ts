import {Component, inject, OnInit} from '@angular/core';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import {faTags, faCalendarDays, faBookOpen} from '@fortawesome/free-solid-svg-icons';

import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {HomeService} from '../home.service';
import {BookResDTO} from '../interface';
import {NgIf} from '@angular/common';
@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [NzInputModule, NzIconModule, FaIconComponent, NgIf],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit {
  faTags = faTags;
  faCalendar = faCalendarDays;
  faBookOpen = faBookOpen;

  homeService = inject(HomeService);


  dataBook: BookResDTO[] = [];


  ngOnInit() {
    this.getDataBook();
  }

  getDataBook(){
    this.homeService.getAllData().subscribe(res => {
     this.dataBook = res;
    })
  }


}
