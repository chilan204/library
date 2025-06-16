import {Component, inject, OnInit} from '@angular/core';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {HomeService} from '../../landing-page/home/home.service';
import {AuthorReqDTO} from '../../landing-page/home/interface';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-add-author-admin',
  imports: [ReactiveFormsModule, NzButtonModule, NzFormModule, NzInputModule],
  templateUrl: './add-author-admin.component.html',
  styleUrl: './add-author-admin.component.scss'
})
export class AddAuthorAdminComponent  implements OnInit {
  data = inject(NZ_MODAL_DATA);
  ref = inject(NzModalRef);
  homeService = inject(HomeService);
  toast = inject(ToastrService);
  isEdit = false;
  private fb = inject(NonNullableFormBuilder);
  validateForm = this.fb.group({
    name: this.fb.control('', [Validators.required]),
    description: this.fb.control('', [Validators.required]),
    birthDate: this.fb.control(new Date(), [Validators.required]),
  });

  ngOnInit(): void {
    if (this.data && this.data.id > 0) {
      this.isEdit = true;
      this.homeService.getDataById3(this.data.id).subscribe((res) => {
        if (res) {
          this.validateForm.patchValue({
            name: res.name,
            description: res.description,
            birthDate: res.birthDate,
          });
        }
      });
    }
  }

  submitForm() {
    if (this.validateForm.valid) {
      console.log(this.validateForm.value);
      const formData = this.validateForm.getRawValue();
      const data: AuthorReqDTO = {
        name: formData.name,
        description: formData.description,
        birthDate: formData.birthDate,

      };
      if (this.isEdit) {
        this.homeService.updateData3(this.data.id, data).subscribe((res) => {
          if (res) {
            this.toast.success('Cập nhật thành công!', 'Update Success');
            this.closeModal();
          }
        });
      } else {
        this.homeService.createData3(data).subscribe({
          next: () => {
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
    this.ref.close();
  }
}
