import { Component, inject, OnInit } from '@angular/core';
import { NgForOf } from '@angular/common';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzTableModule} from 'ng-zorro-antd/table';
import { NzModalModule, NzModalService } from 'ng-zorro-antd/modal';
import {HomeService} from '../../landing-page/home/home.service';
import {BorrowingResDTO} from '../../landing-page/home/interface';
import {AddBorrowingAdminComponent} from '../add-borrowing-admin/add-borrowing-admin.component';
@Component({
  selector: 'app-list-borrowing-admin',
  imports: [
    NzImageModule,
    NzIconModule,
    NzTableModule,
    NzCardModule,
    NzButtonModule,
    NgForOf,
    NzModalModule
  ],
  standalone: true,
  templateUrl: './list-borrowing-admin.component.html',
  styleUrl: './list-borrowing-admin.component.scss'
})
export class ListBorrowingAdminComponent implements OnInit {
  dataService = inject(HomeService);
  modal = inject(NzModalService);
  // khai báo biến
  listData: BorrowingResDTO[] = [];

  ngOnInit() {
    this.getAllData();
  }

  getAllData() {
    this.dataService.getAllData4().subscribe((res) => {
      this.listData = res;
    });
  }
  editData(id: number) {
    this.openPopup(id, 'Chỉnh sửa tiêu đề');
  }

  addData() {
    this.openPopup(0, 'Thêm tiêu đề');
  }

  openPopup(id: number, title: string) {
    this.modal
      .create({
        nzTitle: title,
        nzContent: AddBorrowingAdminComponent,
        nzFooter: null,
        nzWidth: '700px',
        nzMaskClosable: false,
        nzData: {
          id: id,
          title: title
        },
        nzStyle: {
          top: '5px',
          maxHeight: '800px',
          overflow: 'auto'
        }
      })
      .afterClose.subscribe((res) => {
      console.log(res);
      this.getAllData();
    });
  }

  deleteData(id: number) {
    this.dataService.deleteData4(id).subscribe({
      next: () => {
        this.getAllData();
      }
    });
  }

  // Field Title
  showDeleteConfirm(id: number, username: string) {
    this.modal.confirm({
      nzTitle: 'Are you sure delete this task?',
      nzContent: `<b style="color: red;">${username}</b>`,
      nzOkText: 'Yes',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => this.deleteData(id),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel')
    });
  }
}
