import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CardDeck} from "../model/cardDeck.modele";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Card} from "../model/card.modele";


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};


@Injectable({
  providedIn: 'root'
})
export class CardDeckService {

  constructor(private httpClient: HttpClient) {

  }

  cardDecks?: CardDeck[];


  //Get All card deck
  getAll() {
    return this.httpClient.get<CardDeck[]>(environment.API_URL + '/cardDecks');
  }

  //Delete card deck
  deleteCardDeck(id : number) {
    const url = `${environment.API_URL}/cardDecks/${id}`;
    return this.httpClient.delete(url, httpOptions);
  }

  //Update
  showCardDeck(id: number): Observable<CardDeck> {
    const url = `${environment.API_URL}/cardDecks/${id}`;
    return this.httpClient.get<Card>(url);
  }
  updateCardDeck(cardDeck : CardDeck) : Observable<CardDeck>
  {
    const url = `${environment.API_URL}/cardDecks/${cardDeck.id}`;
    return this.httpClient.put<CardDeck>(url, cardDeck);
  }


  //Create
  createCardDeck(cardDeck : CardDeck) : Observable<CardDeck>
  {
    return this.httpClient.post<CardDeck>(environment.API_URL + '/cardDecks', cardDeck);
  }
}
