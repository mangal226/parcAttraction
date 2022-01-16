import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccueilCaissierComponent } from './accueil-caissier.component';

describe('AccueilCaissierComponent', () => {
  let component: AccueilCaissierComponent;
  let fixture: ComponentFixture<AccueilCaissierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccueilCaissierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccueilCaissierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
