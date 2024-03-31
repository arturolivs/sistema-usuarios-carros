import { Component, Inject } from '@angular/core';
import { MatDialogRef,MatDialogContent,MatDialogActions ,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

import { User } from '../../models/user.model';

@Component({
  selector: 'app-remove-user-dialog',
  standalone: true,
  imports: [MatDialogContent, MatDialogActions, MatButtonModule],
  templateUrl: './remove-user-dialog.component.html',
  styleUrl: './remove-user-dialog.component.scss'
})
export class RemoveUserDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<RemoveUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User
 ) {}

 onNoClick(): void {
    this.dialogRef.close();
 }
}

