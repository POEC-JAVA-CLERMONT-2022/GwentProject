import { Component, OnInit } from '@angular/core';
import {Card} from "../../../model/card.modele";
import {CardService} from "../../../service/card.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-card',
  templateUrl: './home-card.component.html',
  styleUrls: ['./home-card.component.css']
})
export class HomeCardComponent implements OnInit {

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

}
