import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCardDeckComponent } from './create-card-deck.component';

describe('CreateCardDeckComponent', () => {
  let component: CreateCardDeckComponent;
  let fixture: ComponentFixture<CreateCardDeckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCardDeckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCardDeckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
