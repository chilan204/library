import { inject } from '@angular/core';
import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import {AuthService} from '../service/auth.service';
import {environment} from '../../../../environments/environment';
import {NzMessageService} from 'ng-zorro-antd/message';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const message = inject(NzMessageService);
  const apiUrl = environment.API_URL;

  // Khai báo danh sách URL cần loại trừ (không cần token)
  const excludedPostUrls: string[] = [
    `${apiUrl}/auth/login`,
  ];
  // 	Là request GET đến API → không thêm token.
  const isGetApiRequest = req.method === 'GET' && req.url.startsWith(`${apiUrl}/`);

  // 	Là request POST đến /auth/login hoặc /customer-contact → không thêm token.
  const isExcludedPost = req.method === 'POST' && excludedPostUrls.includes(req.url);

  // Nếu true thì không cần thêm token.
  const shouldExclude = isGetApiRequest || isExcludedPost;

  // Thêm Authorization header nếu không bị loại trừ
  if (!shouldExclude) {
    const accessToken = localStorage.getItem('auth_token');
    if (accessToken) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
    }
  }
  // Gửi request và xử lý lỗi
  return next(req).pipe(
    catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 403) {
        message.error('Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại.');
        authService.logout();
      }
      return throwError(() => error);
    })
  );
};
