import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichePersoOperateurComponent } from './fiche-perso-operateur.component';

describe('FichePersoOperateurComponent', () => {
  let component: FichePersoOperateurComponent;
  let fixture: ComponentFixture<FichePersoOperateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FichePersoOperateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FichePersoOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
