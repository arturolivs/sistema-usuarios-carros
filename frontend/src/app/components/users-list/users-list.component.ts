import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';

import { User } from '../../models/user.model';
import { RemoveUserDialogComponent } from '../remove-user-dialog/remove-user-dialog.component';

const MOCK_USERS: User[] = [
  {id: 1, firstName: 'Hydrogen', lastName: 'Hydrogen last',  email: 'Hydrogen@email.com' , login: 'Hydrogen_login'},
  {id: 2, firstName: 'Helium', lastName: 'Helium last',  email: 'Helium@email.com' , login: 'Helium_login'},
  {id: 3, firstName: 'Lithium', lastName: 'Lithium last',  email: 'Lithium@email.com' , login: 'Lithium_login'},
  {id: 4, firstName: 'Beryllium', lastName: 'Beryllium last',  email: 'Beryllium@email.com' , login: 'Beryllium_login'},
  {id: 5, firstName: 'Boron', lastName: 'Boron last',  email: 'Boron@email.com', login: 'Boron_login'},
  {id: 6, firstName: 'Carbon', lastName: 'Carbon last',  email: 'Carbon@email.com', login: 'Carbon_login'},
  {id: 7, firstName: 'Nitrogen', lastName: 'Nitrogen last',  email: 'Nitrogen@email.com', login: 'Nitrogen_login'},
  {id: 8, firstName: 'Oxygen', lastName: 'Oxygen last',  email: 'Oxygen@email.com', login: 'Oxygen_login'},
  {id: 9, firstName: 'Fluorine', lastName: 'Fluorine last',  email: 'Fluorine@email.com', login: 'Fluorine_login'},
  {id: 10, firstName: 'Neon', lastName: 'Neon last',  email: 'Neon@email.com', login: 'Neon_login' }
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

  constructor(public dialog: MatDialog) {}

  openRemoveDialog(user: User): void {
    const dialogRef = this.dialog.open(RemoveUserDialogComponent, {
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      // Aqui você pode adicionar a lógica para remover o usuário se o resultado for positivo
    });
  }

}
