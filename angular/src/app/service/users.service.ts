import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model/users.modele";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";


const httpOptions = {
  headers: new HttpHeaders( {'Content-Type': 'application/json'} )
};

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private httpClient: HttpClient) {

  }

  users?: User[];


  //Get All user
  getAll() {
    return this.httpClient.get<User[]>(environment.API_URL + '/user');
  }

}
