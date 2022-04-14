import { TestBed } from '@angular/core/testing';

import { addNewCarService } from './addNewCarService';

describe('ParcheggioserviceService', () => {
  let service: addNewCarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(addNewCarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
