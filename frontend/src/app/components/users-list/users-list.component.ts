import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';


import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';

import { User } from '../../models/user.model';
import { RemoveUserDialogComponent } from '../remove-user-dialog/remove-user-dialog.component';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-users-list',
  standalone: true,
  imports: [MatTableModule, MatIconModule, MatButtonModule, RouterModule],
  templateUrl: './users-list.component.html',
  styleUrl: './users-list.component.scss'
})
export class UsersListComponent {
  displayedColumns: string[] = ['firstName', 'lastName', 'email','login' ,'actions'];
  data: User[] = [];

  constructor(
    private userService: UserService,
    public dialog: MatDialog,
    private router: Router) {}


    ngOnInit(): void {
      this.userService.getAllUsers().subscribe(users => {
        this.data = users;
      });
   }

  editUser(userId: number): void {
    this.router.navigate(['/user-edit-form', userId]);
   }

   openRemoveDialog(user: User): void {
    const dialogRef = this.dialog.open(RemoveUserDialogComponent, {
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.userService.removeUser(Number(user.id)).subscribe(() => {
          this.userService.getAllUsers().subscribe(users => {
            this.data = users;
          });
        });
      }
    });
 }

}
