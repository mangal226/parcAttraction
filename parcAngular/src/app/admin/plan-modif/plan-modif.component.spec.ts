import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlanModifComponent } from './plan-modif.component';

describe('PlanModifComponent', () => {
  let component: PlanModifComponent;
  let fixture: ComponentFixture<PlanModifComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlanModifComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlanModifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
