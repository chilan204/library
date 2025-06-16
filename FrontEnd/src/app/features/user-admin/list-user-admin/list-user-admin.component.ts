import { Component, inject, OnInit } from '@angular/core';
import { NgForOf } from '@angular/common';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzTableModule} from 'ng-zorro-antd/table';
import { NzModalModule, NzModalService } from 'ng-zorro-antd/modal';
import {HomeService} from '../../landing-page/home/home.service';
import {UserResDTO} from '../../landing-page/home/interface';
import {AddUserAdminComponent} from '../add-user-admin/add-user-admin.component';
@Component({
  selector: 'app-list-user-admin',
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
  templateUrl: './list-user-admin.component.html',
  styleUrl: './list-user-admin.component.scss'
})
export class ListUserAdminComponent implements OnInit {
  dataService = inject(HomeService);
  modal = inject(NzModalService);
  // khai báo biến
  listData: UserResDTO[] = [];

  ngOnInit() {
    this.getAllData();
  }

  getAllData() {
    this.dataService.getAllData5().subscribe((res) => {
      this.listData = res;
    });
  }
  editData(id: number) {
    this.openPopup(id, 'Chỉnh sửa người dùng');
  }

  addData() {
    this.openPopup(0, 'Thêm người dùng');
  }

  openPopup(id: number, title: string) {
    this.modal
      .create({
        nzTitle: title,
        nzContent: AddUserAdminComponent,
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
      if (res === 'success') {
        this.getAllData();
      }
    });
  }

  deleteData(id: number) {
    this.dataService.deleteData5(id).subscribe({
      next: () => {
        this.getAllData();
      }
    });
  }

  // Field Title
  showDeleteConfirm(id: number, username: string) {
    this.modal.confirm({
      nzTitle: 'Bạn có muốn xóa?',
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
