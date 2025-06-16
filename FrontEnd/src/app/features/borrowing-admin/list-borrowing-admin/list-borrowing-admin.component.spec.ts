import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBorrowingAdminComponent } from './list-borrowing-admin.component';

describe('ListBorrowingAdminComponent', () => {
  let component: ListBorrowingAdminComponent;
  let fixture: ComponentFixture<ListBorrowingAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListBorrowingAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListBorrowingAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
