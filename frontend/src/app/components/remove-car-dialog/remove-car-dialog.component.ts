import { Component, Inject } from '@angular/core';
import { MatDialogRef,MatDialogContent,MatDialogActions ,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

import { Car } from '../../models/car.model';

@Component({
  selector: 'app-remove-car-dialog',
  standalone: true,
  imports: [MatDialogContent, MatDialogActions, MatButtonModule],
  templateUrl: './remove-car-dialog.component.html',
  styleUrl: './remove-car-dialog.component.scss'
})
export class RemoveCarDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<RemoveCarDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Car
 ) {}

 onNoClick(): void {
    this.dialogRef.close();
 }
}

