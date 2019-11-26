import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyvidComponent } from './myvid.component';

describe('MyvidComponent', () => {
  let component: MyvidComponent;
  let fixture: ComponentFixture<MyvidComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyvidComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyvidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
