import { TestBed } from '@angular/core/testing';

import { AtttractionService } from './atttraction.service';

describe('AtttractionService', () => {
  let service: AtttractionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtttractionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
