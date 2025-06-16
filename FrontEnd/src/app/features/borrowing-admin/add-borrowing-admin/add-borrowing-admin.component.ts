import {Component, inject, OnInit} from '@angular/core';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import {FormBuilder,ReactiveFormsModule, Validators} from '@angular/forms';
import {HomeService} from '../../landing-page/home/home.service';
import { BookResDTO, BorrowingReqDTO, UserResDTO} from '../../landing-page/home/interface';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { NzSelectModule } from 'ng-zorro-antd/select';
@Component({
  selector: 'app-add-borrowing-admin',
  imports: [ NzInputModule, ReactiveFormsModule, NzButtonModule, NzFormModule, NzInputModule, NzSelectModule],
  templateUrl: './add-borrowing-admin.component.html',
  styleUrl: './add-borrowing-admin.component.scss'
})
export class AddBorrowingAdminComponent  implements OnInit {
  data = inject(NZ_MODAL_DATA);

  ref = inject(NzModalRef);
  private fb = inject(FormBuilder);
  homeService = inject(HomeService);
  toast = inject(ToastrService);

  // Khai báo biến
  isEdit = false;
  listUsers : UserResDTO[]=[];
  listBook : BookResDTO[]=[];

  // validateForm
  validateForm = this.fb.group({
    userId: this.fb.control<number | null>(null, [Validators.required]),
    bookId: this.fb.control<number | null>(null, [Validators.required]),
    borrowDate: this.fb.control<Date | null>(null, [Validators.required]),
    dueDate: this.fb.control<Date | null>(null, [Validators.required]),
    returnDate: this.fb.control<Date | null>(null),
  });

  // ngOnInit
  ngOnInit(): void {
    if (this.data && this.data.id > 0) {
      this.isEdit = true;
      this.homeService.getDataById4(this.data.id).subscribe((res) => {
        if (res) {
          this.validateForm.patchValue({
            userId: res.user.id,
            bookId: res.book.id,
            borrowDate: res.borrowDate,
            dueDate: res.dueDate,
            returnDate: res.returnDate,
          });
        }
      });
    }
    this.getDataAuthor();
    this.getDataCategory();
  }
  // submitForm
  submitForm() {
    if (this.validateForm.valid) {
      console.log(this.validateForm.value);
      const formData = this.validateForm.getRawValue();
      const data: BorrowingReqDTO = {
        userId: formData.userId as number,
        bookId: formData.bookId as number,
        borrowDate: formData.borrowDate as Date ,
        dueDate: formData.dueDate as Date,
        returnDate: formData.returnDate as Date,
      };
      if (this.isEdit) {
        this.homeService.updateData4(this.data.id, data).subscribe((res) => {
          if (res) {
            this.toast.success('Cập nhật thành công!', 'Update Success');
            this.closeModal();
          }
        });
      } else {
        this.homeService.createData4(data).subscribe({
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
  // getDataAuthor
  getDataAuthor(){
    this.homeService.getAllData5().subscribe((res) => {
      this.listUsers = res;
    })
  }
  getDataCategory(){
    this.homeService.getAllData().subscribe((res) => {
      this.listBook = res;
    })
  }
}

