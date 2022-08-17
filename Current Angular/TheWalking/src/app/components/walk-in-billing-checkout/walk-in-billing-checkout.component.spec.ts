import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WalkInBillingCheckoutComponent } from './walk-in-billing-checkout.component';

describe('WalkInBillingCheckoutComponent', () => {
  let component: WalkInBillingCheckoutComponent;
  let fixture: ComponentFixture<WalkInBillingCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WalkInBillingCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WalkInBillingCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
