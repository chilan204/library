import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import {AuthService} from '../service/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  private authService = inject(AuthService);
  private router = inject(Router);
  toastService = inject(NzNotificationService);

  // function
  canActivate(): boolean {
    // Kiểm tra xem đăng nhập hay chưa
    if (!this.authService.isAuthenticated()) {
      this.toastService.error('Lỗi', "Bạn chưa đăng nhập hoặc đăng nhập hết hạn!");
      this.router.navigate(['/']);
      return false;
    }

    // Đã đăng nhập, kiểm tra vai trò
    // if (this.authService.role() === 'ADMIN') {
    //   return true;
    // } else if (this.authService.role() === 'USER') {
    //   this.router.navigate(['/landing']);
    //   return true;
    // }
    return true;
  }
}

