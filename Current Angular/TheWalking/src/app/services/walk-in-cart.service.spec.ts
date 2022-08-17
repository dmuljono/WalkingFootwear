import { TestBed } from '@angular/core/testing';

import { WalkInCartService } from './walk-in-cart.service';

describe('WalkInCartService', () => {
  let service: WalkInCartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalkInCartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
