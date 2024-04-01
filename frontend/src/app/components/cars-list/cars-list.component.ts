import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RouterModule } from '@angular/router';


import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';

import { Car } from '../../models/car.model';
import { CarService } from '../../services/car.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cars-list',
  standalone: true,
  imports: [MatTableModule,MatButtonModule, MatIconModule,RouterModule],
  templateUrl: './cars-list.component.html',
  styleUrl: './cars-list.component.scss'
})
export class CarsListComponent {
  displayedColumns: string[] = ['year', 'licensePlate', 'model', 'color', 'actions'];
  data: Car[] = [];

  constructor(
     private carService: CarService,
     public dialog: MatDialog,
     private router: Router
  ) {}

  ngOnInit(): void {
     this.carService.getAllCars().subscribe(cars => {
       this.data = cars;
     });
  }

  editCar(carId: number): void {
    this.router.navigate(['/car-form', carId]);
   }
 }

