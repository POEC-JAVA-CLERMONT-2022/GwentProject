import { Component, OnInit } from '@angular/core';
import {Card} from "../../../model/card.modele";
import {CardService} from "../../../service/card.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-card',
  templateUrl: './create-card.component.html',
  styleUrls: ['./create-card.component.css']
})
export class CreateCardComponent implements OnInit {

  creationForm: FormGroup;

  error = '';

  constructor(private cardService: CardService, private router :Router) {
    this.creationForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      picture: new FormControl(null, Validators.required),
      powerLvl: new FormControl(null, Validators.required),
      description: new FormControl(null, Validators.required),
      location: new FormControl(null, Validators.required),
      ability: new FormControl(null, Validators.required),
      rowName: new FormControl(null, Validators.required),
      type: new FormControl(null, Validators.required),
    });
  }

  ngOnInit(): void {
  }

  creationCard() {

    this.error = '';

    if(this.creationForm.valid) {
      const data: any = this.creationForm.value;

      console.log({data});

      this.cardService.createCard(data).subscribe(response => {
        console.log('Card created');
        this.router.navigate(['card']).then(() => {
          window.location.reload();})
      });
    } else {
      this.error = `Formulaire as some errors`;
    }
  }
}
/*newCard = new Card();

constructor(private cardService : CardService,
            private router :Router) { }

ngOnInit(): void {

}

createCard(){
  this.cardService.createCard(this.newCard).subscribe(card => {
    console.log(card);
  });
  this.router.navigate(['card']).then(() => {
    window.location.reload();
  });
}
}*/
