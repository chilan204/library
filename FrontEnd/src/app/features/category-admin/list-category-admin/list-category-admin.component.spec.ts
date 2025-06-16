import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCategoryAdminComponent } from './list-category-admin.component';

describe('ListCategoryAdminComponent', () => {
  let component: ListCategoryAdminComponent;
  let fixture: ComponentFixture<ListCategoryAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListCategoryAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCategoryAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
