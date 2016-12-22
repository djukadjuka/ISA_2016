/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ViewRestaurantsService } from './view-restaurants.service';

describe('Service: Test', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewRestaurantsService]
    });
  });

  it('should ...', inject([ViewRestaurantsService], (service: ViewRestaurantsService) => {
    expect(service).toBeTruthy();
  }));
});