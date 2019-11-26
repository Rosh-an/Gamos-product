import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoBotComponent } from './video-bot.component';

describe('VideoBotComponent', () => {
  let component: VideoBotComponent;
  let fixture: ComponentFixture<VideoBotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VideoBotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoBotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
