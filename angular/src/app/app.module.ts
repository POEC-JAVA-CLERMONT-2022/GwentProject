import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { AppRoutingModule } from './routeur/app-routing.module';
import { AppComponent } from './layout/root/app.component';
import { CardComponent } from './component/cards/card/card.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './layout/header/header.component';
import { BodyComponent } from './layout/body/body.component';
import { CardDeckComponent } from './component/card-decks/card-deck/card-deck.component';
import { UpdateCardComponent } from './component/cards/update-card/update-card.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateCardComponent } from './component/cards/create-card/create-card.component';
import { UpdateCardDeckComponent } from './component/card-decks/update-card-deck/update-card-deck.component';
import { CreateCardDeckComponent } from './component/card-decks/create-card-deck/create-card-deck.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { UserComponent } from './component/users/user/user.component';
import { HomeCardComponent } from './component/cards/home-card/home-card.component';


@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    HeaderComponent,
    BodyComponent,
    AppComponent,
    CardDeckComponent,
    UpdateCardComponent,
    CreateCardComponent,
    UpdateCardDeckComponent,
    CreateCardDeckComponent,
    UserComponent,
    HomeCardComponent,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        Ng2SearchPipeModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
