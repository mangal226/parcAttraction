import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderCaissierComponent } from './header-caissier.component';

describe('HeaderCaissierComponent', () => {
  let component: HeaderCaissierComponent;
  let fixture: ComponentFixture<HeaderCaissierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderCaissierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderCaissierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
