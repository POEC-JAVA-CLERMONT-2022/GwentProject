import { Component, OnInit } from '@angular/core';
import {Card} from "../../../model/card.modele";
import {ActivatedRoute, Router} from "@angular/router";
import {CardService} from "../../../service/card.service";

@Component({
  selector: 'app-update-card',
  templateUrl: './update-card.component.html',
  styleUrls: ['./update-card.component.css']
})
export class UpdateCardComponent implements OnInit {

  currentCard = new Card();

  constructor(private activatedRoute: ActivatedRoute,
              private router :Router,
              private cardService: CardService) { }

  ngOnInit(): void {
    this.cardService.showCard(this.activatedRoute.snapshot.params['id']).
    subscribe( card =>{ this.currentCard = card;
    console.log(card)
    });
  }


  updateCard() {
    this.cardService.updateCard(this.currentCard).subscribe(() => {
        this.router.navigate(['card']);
      },(error) => { alert("error when modification sub"); }
    );
  }

}
