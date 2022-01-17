import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichePaieCaissierComponent } from './fiche-paie-caissier.component';

describe('FichePaieCaissierComponent', () => {
  let component: FichePaieCaissierComponent;
  let fixture: ComponentFixture<FichePaieCaissierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FichePaieCaissierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FichePaieCaissierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
