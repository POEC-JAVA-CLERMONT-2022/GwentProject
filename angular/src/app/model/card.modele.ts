import { CardDeck } from './cardDeck.modele';

export class Card {
  id?: number;
  name?: string;
  picture?: string;
  powerLvl? : number;
  description? : string;
  location? : string;
  cardDeck? : CardDeck;
  ability? : string;
  rowName? : string;
  type? : string;
}
