import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRestaurationComponent } from './update-restauration.component';

describe('UpdateAttractionComponent', () => {
  let component: UpdateRestaurationComponent;
  let fixture: ComponentFixture<UpdateRestaurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateRestaurationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateRestaurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

