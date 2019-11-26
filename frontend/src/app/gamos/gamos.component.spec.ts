import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamosComponent } from './gamos.component';

describe('GamosComponent', () => {
  let component: GamosComponent;
  let fixture: ComponentFixture<GamosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GamosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GamosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
