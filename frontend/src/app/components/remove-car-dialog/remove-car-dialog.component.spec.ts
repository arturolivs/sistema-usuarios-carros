import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCarDialogComponent } from './remove-car-dialog.component';

describe('RemoveCarDialogComponent', () => {
  let component: RemoveCarDialogComponent;
  let fixture: ComponentFixture<RemoveCarDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RemoveCarDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoveCarDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
