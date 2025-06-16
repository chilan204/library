import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUserAdminComponent } from './list-user-admin.component';

describe('ListUserAdminComponent', () => {
  let component: ListUserAdminComponent;
  let fixture: ComponentFixture<ListUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
