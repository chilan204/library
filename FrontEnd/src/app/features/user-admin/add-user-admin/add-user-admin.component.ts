import {Component, inject, OnInit} from '@angular/core';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {HomeService} from '../../landing-page/home/home.service';
import {UserReqDTO} from '../../landing-page/home/interface';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { NzSelectModule } from 'ng-zorro-antd/select';
@Component({
  selector: 'app-add-user-admin',
  imports: [ReactiveFormsModule, NzButtonModule, NzFormModule, NzInputModule, NzSelectModule],
  templateUrl: './add-user-admin.component.html',
  styleUrl: './add-user-admin.component.scss'
})
export class AddUserAdminComponent  implements OnInit {
  data = inject(NZ_MODAL_DATA);

  ref = inject(NzModalRef);
  private fb = inject(NonNullableFormBuilder);
  homeService = inject(HomeService);
  toast = inject(ToastrService);

  // Khai báo biến
  isEdit = false;

  // validateForm
  validateForm = this.fb.group({
    username: this.fb.control<string>('', [Validators.required]),
    email: this.fb.control<string>('', [Validators.required, Validators.email]),
    phone: this.fb.control<string>('', [Validators.required]),
    password: this.fb.control<string>('', [Validators.required]),
    userCode: this.fb.control<string>('', [Validators.required]),
  });
  // ngOnInit
  ngOnInit(): void {
    if (this.data && this.data.id > 0) {
      this.isEdit = true;
      this.homeService.getDataById5(this.data.id).subscribe((res) => {
        if (res) {
          this.validateForm.patchValue({
            username: res.username,
            email: res.email,
            phone: res.phone,
            password: res.password,
            userCode: res.userCode,
          });
        }
      });
    }
  }
  // submitForm
  submitForm() {
    if (this.validateForm.valid) {
      console.log(this.validateForm.value);
      const formData = this.validateForm.getRawValue();
      const data: UserReqDTO = {
        username: formData.username,
        email: formData.email,
        phone: formData.phone,
        password: formData.password,
        userCode: formData.userCode,
      };
      if (this.isEdit) {
        this.homeService.updateData5(this.data.id, data).subscribe((res) => {
          if (res) {
            this.toast.success('Cập nhật thành công!', 'Update Success');
            this.closeModal();
          }
        });
      } else {
        this.homeService.createData5(data).subscribe({
          next: () => {
            console.log(data);
            this.toast.success('Saved successfully!', 'Success');
            this.closeModal();
          },
          error: () => {
            this.toast.error('Oops! Something went wrong!', 'Error');
          }
        });
      }
    } else {
      Object.values(this.validateForm.controls).forEach((control) => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
  closeModal(): void {
    this.validateForm.reset();
    this.ref.close('success');
  }

}

