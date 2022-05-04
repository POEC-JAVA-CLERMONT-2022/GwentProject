import { Component, OnInit } from '@angular/core';
import {User} from "../../../model/users.modele";
import {Router} from "@angular/router";
import {UsersService} from "../../../service/users.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users : User[];

  constructor(private usersService : UsersService, private router :Router) {

    this.users=[];
  }

  ngOnInit(): void {
    this.usersService.getAll().subscribe(data => {
      this.users = data;
      console.log(data);
    });
    console.log("Users = " , this.users);
  }

}
