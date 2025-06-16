import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBooksAdminComponent } from './add-books-admin.component';

describe('AddBooksAdminComponent', () => {
  let component: AddBooksAdminComponent;
  let fixture: ComponentFixture<AddBooksAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddBooksAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddBooksAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
