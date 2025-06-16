import { Component, inject } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {NzFormModule} from 'ng-zorro-antd/form';
import { NzInputModule} from 'ng-zorro-antd/input';
import { NzButtonModule} from 'ng-zorro-antd/button';
import { NzCheckboxModule} from 'ng-zorro-antd/checkbox';
import {ToastrService} from 'ngx-toastr';
import {AuthService} from '../../core/service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, NzButtonModule, NzCheckboxModule, NzFormModule, NzInputModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  styles: [
    `
      .login-form {
        max-width: 400px;
      }

      .login-form-margin {
        margin-bottom: 16px;
      }

      .login-form-forgot {
        float: right;
      }

      .login-form-button {
        width: 100%;
      }
    `
  ]
})
export class LoginComponent {
  private fb = inject(NonNullableFormBuilder);
  authService = inject(AuthService);
  loginForm = this.fb.group({
    username: this.fb.control('', [Validators.required]),
    password: this.fb.control('', [Validators.required])
  });

  // authService = inject(AuthService);

  submitForm(): void {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value);

    }
    if (this.loginForm.valid) {
      console.log(this.loginForm.value.username, this.loginForm.value.password);
    }
    else {
      Object.values(this.loginForm.controls).forEach((control) => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
