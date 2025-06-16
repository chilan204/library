import {Component, inject, OnInit} from '@angular/core';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {HomeService} from '../../landing-page/home/home.service';
import {AuthorResDTO, BookReqDTO, CategoryResDTO} from '../../landing-page/home/interface';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';
import { ToastrService } from 'ngx-toastr';
import { NzSelectModule } from 'ng-zorro-antd/select';
@Component({
  selector: 'app-add-books-admin',
  imports: [ReactiveFormsModule, NzButtonModule, NzFormModule, NzInputModule, NzSelectModule],
  templateUrl: './add-books-admin.component.html',
  styleUrl: './add-books-admin.component.scss'
})
export class AddBooksAdminComponent  implements OnInit {
  data = inject(NZ_MODAL_DATA);

  ref = inject(NzModalRef);
  private fb = inject(NonNullableFormBuilder);
  homeService = inject(HomeService);
  toast = inject(ToastrService);

  // Khai báo biến
  isEdit = false;
  listAuthors : AuthorResDTO[]=[];
  listCategory : CategoryResDTO[]=[];

  // validateForm
  validateForm = this.fb.group({
    name: this.fb.control('', [Validators.required]),
    description: this.fb.control('', [Validators.required]),
    authorId: this.fb.control(0, [Validators.required]),
    publicationYear: this.fb.control(0, [Validators.required]),
    image: this.fb.control('', [Validators.required]),
    categoryId: this.fb.control(0, [Validators.required]),
  });
  // ngOnInit
  ngOnInit(): void {
    if (this.data && this.data.id > 0) {
      this.isEdit = true;
      this.homeService.getDataById(this.data.id).subscribe((res) => {
        if (res) {
          this.validateForm.patchValue({
            name: res.name,
            description: res.description,
            authorId: res.author?.id,
            publicationYear: res.publicationYear,
            image: res.image,
            categoryId: res.category?.id,
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
      const data: BookReqDTO = {
        name: formData.name,
        description: formData.description,
        authorId: formData.authorId,
        publicationYear: formData.publicationYear,
        categoryId: formData.categoryId,
        image: formData.image,
      };
      if (this.isEdit) {
        this.homeService.updateData(this.data.id, data).subscribe((res) => {
          if (res) {
            this.toast.success('Cập nhật thành công!', 'Update Success');
            this.closeModal();
          }
        });
      } else {
        this.homeService.createData(data).subscribe({
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

  //   getDataAuthor
  getDataAuthor(){
    this.homeService.getAllData3().subscribe((res) => {
      this.listAuthors = res;
    })
  }
  getDataCategory(){
    this.homeService.getAllData2().subscribe((res) => {
      this.listCategory = res;
    })
  }
}

