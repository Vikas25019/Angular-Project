import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewClientPageComponent } from './view-client-page.component';

describe('ViewClientPageComponent', () => {
  let component: ViewClientPageComponent;
  let fixture: ComponentFixture<ViewClientPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewClientPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewClientPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
