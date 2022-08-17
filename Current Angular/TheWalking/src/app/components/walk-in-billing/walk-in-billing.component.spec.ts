import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WalkInBillingComponent } from './walk-in-billing.component';

describe('WalkInBillingComponent', () => {
  let component: WalkInBillingComponent;
  let fixture: ComponentFixture<WalkInBillingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WalkInBillingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WalkInBillingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
