import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CardDeck} from "../../../model/cardDeck.modele";
import {CardDeckService} from "../../../service/card-deck.service";
import {Card} from "../../../model/card.modele";

@Component({
  selector: 'app-card-deck',
  templateUrl: './card-deck.component.html',
  styleUrls: ['./card-deck.component.css']
})
export class CardDeckComponent implements OnInit {

  cardDecks : CardDeck[];

  constructor(private cardDeckService : CardDeckService,
              private router :Router) {

    this.cardDecks=[];
  }

  ngOnInit(): void {
    this.cardDeckService.getAll().subscribe(data => {
      this.cardDecks = data;
      console.log(data);
    });
    console.log("Card deck = " , this.cardDecks);
  }


  //delete cardDeck
  deleteCardDeck(cd: CardDeck)
  {
    console.log("delete");
    let confirmation = confirm("Are u sure ?");
    if (confirmation)
      this.cardDeckService.deleteCardDeck(cd.id!).subscribe(() => {
        console.log("cardDeck deleted");
        this.DeleteCardDeckOfBdd(cd);
      });
  }

  DeleteCardDeckOfBdd(cardDeck : CardDeck) {
    this.cardDecks.forEach((cur, index) => {
      if(cardDeck.id === cur.id) {
        this.cardDecks.splice(index, 1);
      }
    });
  }

}
