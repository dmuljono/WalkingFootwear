import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewWalkInOrdersComponent } from './view-walk-in-orders.component';

describe('ViewWalkInOrdersComponent', () => {
  let component: ViewWalkInOrdersComponent;
  let fixture: ComponentFixture<ViewWalkInOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewWalkInOrdersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewWalkInOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
