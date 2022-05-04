import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CardDeck} from "../../../model/cardDeck.modele";
import {CardDeckService} from "../../../service/card-deck.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-card-deck',
  templateUrl: './create-card-deck.component.html',
  styleUrls: ['./create-card-deck.component.css']
})
export class CreateCardDeckComponent implements OnInit {

  creationForm: FormGroup;

  error = '';

  constructor(private cardDeckService: CardDeckService, private router :Router) {
    this.creationForm = new FormGroup({
      name: new FormControl(null, Validators.required),
    });
  }

  ngOnInit(): void {
  }

  creationCardDeck() {

    this.error = '';

    if(this.creationForm.valid) {
      const data: any = this.creationForm.value;

      console.log({data});

      this.cardDeckService.createCardDeck(data).subscribe(response => {
        console.log('Card deck created');
        this.router.navigate(['card']).then(() => {
          window.location.reload();})
      });
    } else {
      this.error = `Formulaire as some errors`;
    }
  }
}
