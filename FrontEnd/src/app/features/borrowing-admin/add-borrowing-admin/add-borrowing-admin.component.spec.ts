import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBorrowingAdminComponent } from './add-borrowing-admin.component';

describe('AddBorrowingAdminComponent', () => {
  let component: AddBorrowingAdminComponent;
  let fixture: ComponentFixture<AddBorrowingAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddBorrowingAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddBorrowingAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
