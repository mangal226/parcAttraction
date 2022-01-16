import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccueilOperateurComponent } from './accueil-operateur.component';

describe('AccueilOperateurComponent', () => {
  let component: AccueilOperateurComponent;
  let fixture: ComponentFixture<AccueilOperateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccueilOperateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccueilOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
