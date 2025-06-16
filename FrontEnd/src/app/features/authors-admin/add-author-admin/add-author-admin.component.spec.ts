import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAuthorAdminComponent } from './add-author-admin.component';

describe('AddAuthorAdminComponent', () => {
  let component: AddAuthorAdminComponent;
  let fixture: ComponentFixture<AddAuthorAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAuthorAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAuthorAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
