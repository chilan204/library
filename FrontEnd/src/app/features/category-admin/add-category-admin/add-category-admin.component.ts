  import {Component, inject, OnInit} from '@angular/core';
  import { NzFormModule } from 'ng-zorro-antd/form';
  import { NzInputModule } from 'ng-zorro-antd/input';
  import { NzButtonModule } from 'ng-zorro-antd/button';
  import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
  import {HomeService} from '../../landing-page/home/home.service';
  import {CategoryReqDTO} from '../../landing-page/home/interface';
  import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';
  import { ToastrService } from 'ngx-toastr';
  @Component({
    selector: 'app-add-category-admin',
    imports: [ReactiveFormsModule, NzButtonModule, NzFormModule, NzInputModule],
    templateUrl: './add-category-admin.component.html',
    styleUrl: './add-category-admin.component.scss'
  })
  export class AddCategoryAdminComponent  implements OnInit {
    data = inject(NZ_MODAL_DATA);
    ref = inject(NzModalRef);
    homeService = inject(HomeService);
    toast = inject(ToastrService);
    isEdit = false;
    private fb = inject(NonNullableFormBuilder);
    validateForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
    });

    ngOnInit(): void {
      if (this.data && this.data.id > 0) {
        this.isEdit = true;
        this.homeService.getDataById2(this.data.id).subscribe((res) => {
          if (res) {
            this.validateForm.patchValue({
              name: res.name,
            });
          }
        });
      }
    }

    submitForm() {
      if (this.validateForm.valid) {
        console.log(this.validateForm.value);
        const formData = this.validateForm.getRawValue();
        const data: CategoryReqDTO = {
          name: formData.name,
        };
        if (this.isEdit) {
          this.homeService.updateData2(this.data.id, data).subscribe((res) => {
            if (res) {
              this.toast.success('Cập nhật thành công!', 'Update Success');
              this.closeModal();
            }
          });
        } else {
          this.homeService.createData2(data).subscribe({
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
