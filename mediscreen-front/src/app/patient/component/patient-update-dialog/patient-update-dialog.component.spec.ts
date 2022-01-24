import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PatientUpdateDialogComponent} from './patient-update-dialog.component';

describe('PatientUpdateDialogComponent', () => {
  let component: PatientUpdateDialogComponent;
  let fixture: ComponentFixture<PatientUpdateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PatientUpdateDialogComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
