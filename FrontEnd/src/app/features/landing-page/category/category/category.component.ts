import {Component, inject, OnInit} from '@angular/core';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import {faTags, faCalendarDays, faBookOpen} from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {HomeService} from '../../home/home.service';
import {BookResDTO, CategoryResDTO} from '../../home/interface';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-category',
  imports: [NzInputModule, NzIconModule, FaIconComponent, NgIf],
  templateUrl: './category.component.html',
  styleUrl: './category.component.scss'
})
export class CategoryComponent implements OnInit {
  faTags = faTags;
  faCalendar = faCalendarDays;
  faBookOpen = faBookOpen;
  newsId = 0;
  homeService = inject(HomeService);
  route = inject(ActivatedRoute);

  dataDetailCategory: CategoryResDTO | null = null;


  dataBookCategory: BookResDTO []=[];

  loadNewsDetail(): void {
    this.homeService.getDataById2(this.newsId).subscribe({
      next: (data: CategoryResDTO) => {
        this.dataDetailCategory = data;
      },
      error: (err) => {
        console.error('Error loading news detail:', err);
      }
    });
  }

  loadNewsCategory(): void {
    this.homeService.getDataByCategoryId(this.newsId).subscribe({
      next: (data: BookResDTO[]) => {
        this.dataBookCategory = data; // assuming this is also BookResDTO[]
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
        this.loadNewsCategory();
      }
    });
  }


}
