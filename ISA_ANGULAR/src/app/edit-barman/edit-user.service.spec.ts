import { TestBed, async, inject } from '@angular/core/testing';
import { EditUserHelpService } from './edit-userhelp.service';

describe('Service: EditUser', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EditUserHelpService]
    });
  });

  it('should ...', inject([EditUserHelpService], (service: EditUserHelpService) => {
    expect(service).toBeTruthy();
  }));
});
