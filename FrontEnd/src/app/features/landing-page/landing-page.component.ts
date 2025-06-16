import {Component, inject, OnInit} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import {AuthorResDTO, CategoryResDTO} from './home/interface';
import {HomeService} from './home/home.service';
import {NgIf} from '@angular/common';
import {AuthService} from '../../core/service/auth.service';
@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    NzDropDownModule,
    NzIconModule,
    RouterOutlet,
    RouterLink,
    NgIf
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.scss'
})
export class LandingPageComponent implements OnInit {
  dataCategory: CategoryResDTO[] =[];
  dataAuthor: AuthorResDTO[] =[];
  authService = inject(AuthService);
  isLogin = this.authService.isAuthenticated();
  homeService = inject(HomeService);



  logout(){
    this.authService.logout();
  }



  getDataCategory(){
    this.homeService.getAllData2().subscribe(data => {
      this.dataCategory = data;
    })
  }

  getDataAuthors(){
    this.homeService.getAllData3().subscribe(res => {
      this.dataAuthor = res;
    })
  }
  ngOnInit() {
    this.getDataCategory();
    this.getDataAuthors();
  }
}
