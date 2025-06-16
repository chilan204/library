import {Component, inject, OnInit} from '@angular/core';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import {faCalendarDays, faUser} from '@fortawesome/free-solid-svg-icons';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {HomeService} from '../../home/home.service';
import {BorrowingResDTO} from '../../home/interface';
import {NgClass, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault} from '@angular/common';
import {AuthService} from '../../../../core/service/auth.service';
@Component({
  selector: 'app-borrowing',
  imports: [NzTabsModule, FaIconComponent, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault, NgClass],
  templateUrl: './borrowing.component.html',
  styleUrl: './borrowing.component.scss'
})
export class BorrowingComponent implements OnInit {
  faUser = faUser;
  faCalendar = faCalendarDays;
  homeService = inject(HomeService);
  authService=inject(AuthService) ;
  role='';
  userName='';
  listDataBorrowing : BorrowingResDTO[] = [];

  ngOnInit() {
    this.getAllDataBorrowing();
  }
  getAllDataBorrowing(){
    this.role = this.authService.getRole();
    this.userName = this.authService.getUserName();

    if (this.role === 'ADMIN') {
      this.homeService.getAllData4().subscribe((data) => {
        this.listDataBorrowing = data;
      });
    } else {
      this.homeService.getUserData4(this.userName).subscribe((data) => {
        this.listDataBorrowing = data;
      });
    }
  }
}
