import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFormEditComponent } from './user-edit-form.component';

describe('UserFormComponent', () => {
  let component: UserFormEditComponent;
  let fixture: ComponentFixture<UserFormEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserFormEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserFormEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
