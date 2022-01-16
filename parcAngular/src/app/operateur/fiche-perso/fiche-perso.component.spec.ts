import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichePersoComponent } from './fiche-perso.component';

describe('FichePersoComponent', () => {
  let component: FichePersoComponent;
  let fixture: ComponentFixture<FichePersoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FichePersoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FichePersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
