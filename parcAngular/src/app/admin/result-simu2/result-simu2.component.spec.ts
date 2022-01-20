import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultSimu2Component } from './result-simu2.component';

describe('ResultSimu2Component', () => {
  let component: ResultSimu2Component;
  let fixture: ComponentFixture<ResultSimu2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultSimu2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultSimu2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
