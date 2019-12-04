import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PhoneStateSelectorComponent } from './phone-state-selector.component';

describe('PhoneStateSelectorComponent', () => {
  let component: PhoneStateSelectorComponent;
  let fixture: ComponentFixture<PhoneStateSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PhoneStateSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PhoneStateSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
