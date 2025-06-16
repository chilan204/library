
import {Component, inject, OnInit} from '@angular/core';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import {faTags, faCalendarDays, faBookOpen} from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {HomeService} from '../../home/home.service';
import {AuthorResDTO, BookResDTO} from '../../home/interface';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-authors',
  imports: [NzInputModule, NzIconModule, FaIconComponent, NgIf],
  templateUrl: './authors.component.html',
  styleUrl: './authors.component.scss'
})
export class AuthorsComponent implements OnInit {
  faTags = faTags;
  faCalendar = faCalendarDays;
  faBookOpen = faBookOpen;
  newsId = 0;
  homeService = inject(HomeService);
  route = inject(ActivatedRoute);

  dataDetailAuthor: AuthorResDTO | null = null;


  dataBookAuthor: BookResDTO []=[];

  loadNewsDetail(): void {
    this.homeService.getDataById3(this.newsId).subscribe({
      next: (data: AuthorResDTO) => {
        this.dataDetailAuthor = data;
      },
      error: (err) => {
        console.error('Error loading news detail:', err);
      }
    });
  }

  loadNewsAuthor(): void {
    this.homeService.getDataByAuthorId(this.newsId).subscribe({
      next: (data: BookResDTO[]) => {
        this.dataBookAuthor = data; // assuming this is also BookResDTO[]
      },
      error: (err) => {
        console.error('Error loading news detail:', err);
      }
    });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.newsId = Number(idParam);
        this.loadNewsDetail();
      }
    });
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.newsId = Number(idParam);
        this.loadNewsAuthor();
      }
    });
  }


}
