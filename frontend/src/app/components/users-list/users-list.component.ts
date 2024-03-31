import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

import { User } from '../../models/user.model';

const MOCK_USERS: User[] = [
  { firstName: 'Hydrogen', lastName: 'Hydrogen last',  email: 'Hydrogen@email.com' },
  { firstName: 'Helium', lastName: 'Helium last',  email: 'Helium@email.com' },
  { firstName: 'Lithium', lastName: 'Lithium last',  email: 'Lithium@email.com' },
  { firstName: 'Beryllium', lastName: 'Beryllium last',  email: 'Beryllium@email.com' },
  { firstName: 'Boron', lastName: 'Boron last',  email: 'Boron@email.com'},
  { firstName: 'Carbon', lastName: 'Carbon last',  email: 'Carbon@email.com'},
  { firstName: 'Nitrogen', lastName: 'Nitrogen last',  email: 'Nitrogen@email.com'},
  { firstName: 'Oxygen', lastName: 'Oxygen last',  email: 'Oxygen@email.com'},
  { firstName: 'Fluorine', lastName: 'Fluorine last',  email: 'Fluorine@email.com'},
  { firstName: 'Neon', lastName: 'Neon last',  email: 'Neon@email.com' }
];

@Component({
  selector: 'app-users-list',
  standalone: true,
  imports: [MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.scss'
})
export class UsersListComponent {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'actions'];
  dataSource = MOCK_USERS;
}
