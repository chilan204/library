import { Component, inject, OnInit } from '@angular/core';
import { NgForOf } from '@angular/common';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzTableModule} from 'ng-zorro-antd/table';
import { NzModalModule, NzModalService } from 'ng-zorro-antd/modal';
import {HomeService} from '../../landing-page/home/home.service';
import {AuthorResDTO, CategoryResDTO} from '../../landing-page/home/interface';
import {AddAuthorAdminComponent} from '../add-author-admin/add-author-admin.component';

@Component({
  selector: 'app-list-author-admin',
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
  templateUrl: './list-author-admin.component.html',
  styleUrl: './list-author-admin.component.scss'
})
export class ListAuthorAdminComponent implements OnInit {
  dataService = inject(HomeService);
  modal = inject(NzModalService);
  // khai báo biến
  listData: AuthorResDTO[] = [];

  ngOnInit() {
    this.getAllData();
  }

  getAllData() {
    this.dataService.getAllData3().subscribe((res) => {
      this.listData = res;
    });
  }
  editData(id: number) {
    this.openPopup(id, 'Chỉnh sửa tác giả');
  }

  addData() {
    this.openPopup(0, 'Thêm tác giả');
  }

  openPopup(id: number, title: string) {
    this.modal
      .create({
        nzTitle: title,
        nzContent: AddAuthorAdminComponent,
        nzFooter: null,
        nzWidth: '700px',
        nzMaskClosable: false,
        nzData: {
          id: id,
          title: title
        },
        nzStyle: {
          top: '70px',
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
    this.dataService.deleteData3(id).subscribe({
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
