import { Injectable } from '@angular/core';
import { Card } from '../model/card.modele';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from "../../environments/environment";


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};


@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private httpClient: HttpClient) {

  }

  cards?: Card[];


  //Get All card
  getAll() {
    return this.httpClient.get<Card[]>(environment.API_URL + '/cards');
  }

  //Delete card
  deleteCard(id : number) {
    const url = `${environment.API_URL}/cards/${id}`;
    return this.httpClient.delete(url, httpOptions);
  }


  //Update
  showCard(id: number): Observable<Card> {
    const url = `${environment.API_URL}/cards/${id}`;
    return this.httpClient.get<Card>(url);
  }
  updateCard(card : Card) : Observable<Card>
  {
    const url = `${environment.API_URL}/cards/${card.id}`;
    return this.httpClient.put<Card>(url, card);
  }

  //Create
  createCard(card : Card) : Observable<Card>
  {
    return this.httpClient.post<Card>(environment.API_URL + '/cards', card);
  }

  /*find by name
  findByCardsName(name : string)
  {
    const url = `${environment.API_URL}/card-name/${name}`;
    return this.httpClient.get<Card[]>( url );
  }*/

}
