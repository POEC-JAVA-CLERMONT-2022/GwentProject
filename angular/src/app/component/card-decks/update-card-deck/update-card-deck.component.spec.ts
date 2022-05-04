import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCardDeckComponent } from './update-card-deck.component';

describe('UpdateCardDeckComponent', () => {
  let component: UpdateCardDeckComponent;
  let fixture: ComponentFixture<UpdateCardDeckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCardDeckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCardDeckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
