import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichePersoCaissierComponent } from './fiche-perso-caissier.component';

describe('FichePersoCaissierComponent', () => {
  let component: FichePersoCaissierComponent;
  let fixture: ComponentFixture<FichePersoCaissierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FichePersoCaissierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FichePersoCaissierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
