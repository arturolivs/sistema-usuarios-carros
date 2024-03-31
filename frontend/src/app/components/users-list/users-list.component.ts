import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

import { User } from '../../models/user.model';

const MOCK_USERS: User[] = [
  { firstName: 'Hydrogen', lastName: 'Hydrogen last',  email: 'Hydrogen@email.com' , login: 'Hydrogen_login'},
  { firstName: 'Helium', lastName: 'Helium last',  email: 'Helium@email.com' , login: 'Helium_login'},
  { firstName: 'Lithium', lastName: 'Lithium last',  email: 'Lithium@email.com' , login: 'Lithium_login'},
  { firstName: 'Beryllium', lastName: 'Beryllium last',  email: 'Beryllium@email.com' , login: 'Beryllium_login'},
  { firstName: 'Boron', lastName: 'Boron last',  email: 'Boron@email.com', login: 'Boron_login'},
  { firstName: 'Carbon', lastName: 'Carbon last',  email: 'Carbon@email.com', login: 'Carbon_login'},
  { firstName: 'Nitrogen', lastName: 'Nitrogen last',  email: 'Nitrogen@email.com', login: 'Nitrogen_login'},
  { firstName: 'Oxygen', lastName: 'Oxygen last',  email: 'Oxygen@email.com', login: 'Oxygen_login'},
  { firstName: 'Fluorine', lastName: 'Fluorine last',  email: 'Fluorine@email.com', login: 'Fluorine_login'},
  { firstName: 'Neon', lastName: 'Neon last',  email: 'Neon@email.com', login: 'Neon_login' }
];

@Component({
  selector: 'app-users-list',
  standalone: true,
  imports: [MatTableModule, MatIconModule, MatButtonModule, RouterModule],
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.scss'
})
export class UsersListComponent {
  displayedColumns: string[] = ['firstName', 'lastName', 'email','login' ,'actions'];
  dataSource = MOCK_USERS;
}
