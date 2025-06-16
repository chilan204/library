import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAuthorAdminComponent } from './list-author-admin.component';

describe('ListAuthorAdminComponent', () => {
  let component: ListAuthorAdminComponent;
  let fixture: ComponentFixture<ListAuthorAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAuthorAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAuthorAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
