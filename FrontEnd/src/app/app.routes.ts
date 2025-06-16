import { Routes } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {LandingPageComponent} from './features/landing-page/landing-page.component';
import {HomePageComponent} from './features/landing-page/home/home-page/home-page.component';
import {CategoryComponent} from './features/landing-page/category/category/category.component';
import {AuthorsComponent} from './features/landing-page/authors/authors/authors.component';
import {AdminPageComponent} from './components/admin-page/admin-page.component';
import {ListCategoryAdminComponent} from './features/category-admin/list-category-admin/list-category-admin.component';
import {ListAuthorAdminComponent} from './features/authors-admin/list-author-admin/list-author-admin.component';
import {ListBookAdminComponent} from './features/book-admin/list-book-admin/list-book-admin.component';
import {
  ListBorrowingAdminComponent
} from './features/borrowing-admin/list-borrowing-admin/list-borrowing-admin.component';
import { AuthGuard } from './core/guards/auth.guard';
import {ListUserAdminComponent} from './features/user-admin/list-user-admin/list-user-admin.component';
import {BorrowingComponent} from './features/landing-page/borrowing/borrowing/borrowing.component';

export const routes: Routes = [
  {
    path: '',
    component:LoginComponent
  },
  {
    path: 'landing',
    component:LandingPageComponent,
    children: [
      {
        path: '',
        component: HomePageComponent
      },
      {
        path: 'category/:id',
        component: CategoryComponent
      },
      {
        path: 'authors/:id',
        component: AuthorsComponent
      },

      {
        path: 'borrowing-page',
        component: BorrowingComponent,
        canActivate: [AuthGuard],
      }
    ]
  },
  {
    path: 'admin',
    component:AdminPageComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'list-category',
        component: ListCategoryAdminComponent
      },
      {
        path: 'list-author',
        component: ListAuthorAdminComponent
      },
      {
        path: 'list-book',
        component: ListBookAdminComponent
      },
      {
        path: 'list-user',
        component: ListUserAdminComponent
      },
      {
        path: 'list-borrowing',
        component: ListBorrowingAdminComponent,
      },
    ]
  },
];
