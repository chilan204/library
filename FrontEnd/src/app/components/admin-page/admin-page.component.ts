import {Component, inject, OnInit} from '@angular/core';
import { NzContentComponent, NzHeaderComponent, NzLayoutComponent, NzSiderComponent } from 'ng-zorro-antd/layout';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NzIconDirective } from 'ng-zorro-antd/icon';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzMenuDirective, NzMenuItemComponent } from 'ng-zorro-antd/menu';
// import { AuthService } from '../../services/auth.service';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzTooltipDirective } from 'ng-zorro-antd/tooltip';
import {NzModalModule, NzModalService} from 'ng-zorro-antd/modal';
import {AuthService} from '../../core/service/auth.service';
@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [
    NzModalModule,
    NzIconModule,
    NzMenuModule,
    NzLayoutComponent,
    NzContentComponent,
    RouterOutlet,
    NzHeaderComponent,
    NzIconDirective,
    NzMenuDirective,
    NzMenuItemComponent,
    NzSiderComponent,
    RouterLink,
    NzTooltipDirective
  ],
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.scss'
})
export class AdminPageComponent  implements OnInit {
  isCollapsed = false;
  userName = '';
  // auth service
  authService = inject(AuthService);
  private modal = inject(NzModalService)

  getName() {
    this.userName = this.authService.getUserName();
  }
  ngOnInit(): void {
    this.getName();
  }
  //logout
  logout(): void {
    this.authService.logout();
  }
  showConfirmLogout(): void {
    this.modal.confirm({
      nzTitle: 'Bạn có muốn đăng xuất?',
      nzOkText: 'Đăng xuất',
      nzCancelText: 'Hủy',
      nzOnOk: () => this.logout()
    });
  }
}
