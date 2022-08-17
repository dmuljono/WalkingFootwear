import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCancelReturnComponent } from './view-cancel-return.component';

describe('ViewCancelReturnComponent', () => {
  let component: ViewCancelReturnComponent;
  let fixture: ComponentFixture<ViewCancelReturnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCancelReturnComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewCancelReturnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
