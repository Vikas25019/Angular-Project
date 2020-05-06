import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllClientComponent } from './show-all-client.component';

describe('ShowAllClientComponent', () => {
  let component: ShowAllClientComponent;
  let fixture: ComponentFixture<ShowAllClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
