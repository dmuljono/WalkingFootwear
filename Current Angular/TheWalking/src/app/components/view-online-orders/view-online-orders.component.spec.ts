import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOnlineOrdersComponent } from './view-online-orders.component';

describe('ViewOnlineOrdersComponent', () => {
  let component: ViewOnlineOrdersComponent;
  let fixture: ComponentFixture<ViewOnlineOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewOnlineOrdersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewOnlineOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
