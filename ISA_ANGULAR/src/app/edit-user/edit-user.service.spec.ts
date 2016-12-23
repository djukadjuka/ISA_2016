/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { EditUserService } from './edit-user.service';

describe('Service: EditUser', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EditUserService]
    });
  });

  it('should ...', inject([EditUserService], (service: EditUserService) => {
    expect(service).toBeTruthy();
  }));
});
