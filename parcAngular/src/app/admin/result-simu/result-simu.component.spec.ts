import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultSimuComponent } from './result-simu.component';

describe('ResultSimuComponent', () => {
  let component: ResultSimuComponent;
  let fixture: ComponentFixture<ResultSimuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultSimuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultSimuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
