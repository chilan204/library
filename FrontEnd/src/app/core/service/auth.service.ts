import {computed, inject, Injectable, signal} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginRes} from '../models/user.model';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private tokenKey = 'auth_token';
  private roleKey = 'user_role';
  private apiUrl = environment.API_URL;
  private userNameKey = 'user_name';

  httpClient = inject(HttpClient);
  router = inject(Router);
  toast = inject(ToastrService);

  private token =signal<string | null>(localStorage.getItem(this.tokenKey));
  role =signal<string | null>(localStorage.getItem(this.roleKey));
  private userName =signal<string | null>(localStorage.getItem(this.userNameKey));

  // Kiểm tra đăng nhập
  isAuthenticated = computed(() => !!this.token());

  //Lấy tên người dùng
  getUserName(): string {
    return this.userName() || 'Admin';
  }
  getRole(): string {
    return this.role() || '';
  }

  // Quyền đăng nhập
  login(userData: any): void {
    this.httpClient.post<LoginRes>(this.apiUrl + '/auth/login', userData).subscribe({
      next: (res) => {
        // Lưu thông tin đăng nhập
        localStorage.setItem(this.tokenKey, res.token);
        localStorage.setItem(this.roleKey, res.role);
        localStorage.setItem(this.userNameKey, res.username);

        this.token.set(res.token);
        this.role.set(res.role);
        this.userName.set(res.username);

        // Điều hướng theo role
        if (res.role === 'ADMIN') {
          this.router.navigate(['/admin']);
          this.toast.success('Đăng nhập thành công!');
        } else {
          this.router.navigate(['/landing']);
          this.toast.success('Đăng nhập thành công!');
        }
      },
      error: (err) => {
        // 👇 Kiểm tra lỗi từ server trả về
        if (err.status === 401 || err.status === 400) {
          this.toast.error('Sai mật khẩu hoặc tài khoản!', 'Lỗi!');
        } else {
          this.toast.error('Có lỗi vui lòng thử lại sau!', 'Lỗi!');
        }
      }
    });
  }




  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.roleKey);
    localStorage.removeItem(this.userNameKey);
    this.token.set(null);
    this.role.set(null);
    this.userName.set(null); // 👈 clear tên
    this.router.navigate(['/']);
  }

}

