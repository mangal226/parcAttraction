import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichePaieOperateurComponent } from './fiche-paie-operateur.component';

describe('FichePaieOperateurComponent', () => {
  let component: FichePaieOperateurComponent;
  let fixture: ComponentFixture<FichePaieOperateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FichePaieOperateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FichePaieOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
