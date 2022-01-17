import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderOperateurComponent } from './header-operateur.component';

describe('HeaderOperateurComponent', () => {
  let component: HeaderOperateurComponent;
  let fixture: ComponentFixture<HeaderOperateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderOperateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
