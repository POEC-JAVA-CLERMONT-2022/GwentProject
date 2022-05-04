import { Component, OnInit } from '@angular/core';
import {CardService} from "../../../service/card.service";
import {Card} from "../../../model/card.modele";
import { Router } from '@angular/router';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  cards : Card[];
  searchText : string;

  constructor(private cardService : CardService, private router :Router) {

    this.cards=[];
    this.searchText= "";
  }

  ngOnInit(): void {
    this.cardService.getAll().subscribe(data => {
      this.cards = data;
      console.log(data);
    });
    console.log("Card = " , this.cards);
  }


  /*findByCardsName(name : string) {
    this.cardService.findByCardsName(name).subscribe(data => {
      this.cardsByName = data;
      console.log(data);
    });
    console.log("Card by name = " , this.cardsByName);
  }*/

//delete card
  deleteCard(c: Card)
  {
    console.log("delete");
    let confirmation = confirm("Are u sure ?");
    if (confirmation)
      this.cardService.deleteCard(c.id!).subscribe(() => {
        console.log("card deleted");
        this.DeleteCardOfBdd(c);
      });
  }
  DeleteCardOfBdd(card : Card) {
    this.cards.forEach((cur, index) => {
      if(card.id === cur.id) {
        this.cards.splice(index, 1);
      }
    });
  }
}
