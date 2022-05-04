import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CardDeck} from "../../../model/cardDeck.modele";
import {CardDeckService} from "../../../service/card-deck.service";

@Component({
  selector: 'app-update-card-deck',
  templateUrl: './update-card-deck.component.html',
  styleUrls: ['./update-card-deck.component.css']
})
export class UpdateCardDeckComponent implements OnInit {

  currentCardDeck = new CardDeck();

  constructor(private activatedRoute: ActivatedRoute,
              private router :Router,
              private cardDeckService: CardDeckService) { }

  ngOnInit(): void {
    this.cardDeckService.showCardDeck(this.activatedRoute.snapshot.params['id']).
    subscribe( cardDeck =>{ this.currentCardDeck = cardDeck;
      console.log(cardDeck)
    });
  }


  updateCardDeck() {
    this.cardDeckService.updateCardDeck(this.currentCardDeck).subscribe(() => {
        this.router.navigate(['cardDeck']);
      },(error) => { alert("error when modification sub"); }
    );
  }

}
