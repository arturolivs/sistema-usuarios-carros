import { Component } from '@angular/core';

import { FormBuilder,
          FormGroup,
          Validators,
          FormsModule,
          ReactiveFormsModule,
          FormControl,
          FormGroupDirective,
          NgForm, } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {ErrorStateMatcher} from '@angular/material/core';
import { Car } from '../../models/car.model';

import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-car-form',
  standalone: true,
  imports: [MatInputModule,
    MatFormFieldModule,
     MatButtonModule,
     FormsModule,
    ReactiveFormsModule,
    RouterModule],
  templateUrl: './car-form.component.html',
  styleUrl: './car-form.component.scss'
})
export class CarFormComponent {
  carForm: FormGroup;
  title: string = 'Cadastro de Carro';
  matcher = new MyErrorStateMatcher();

  constructor(
     private fb: FormBuilder,
     private carService: CarService,
     private route: ActivatedRoute,
     private router: Router,
     private snackBar: MatSnackBar
  ) {
     this.carForm = this.fb.group({
       year: ['', Validators.required],
       licensePlate: ['', Validators.required],
       model: ['', Validators.required],
       color: ['', Validators.required]
     });
  }

  ngOnInit(): void {
    const carId = this.route.snapshot.paramMap.get('id');

    if (carId) {
      this.title = 'Alterar Carro';
      this.loadCarData(carId);
    }
 }

 loadCarData(carId: string): void {
  this.carService.getCarById(carId).subscribe(car => {
    this.carForm.setValue({
      year: car.year,
      licensePlate: car.licensePlate,
      model: car.model,
      color: car.color
    });
  });
}

  createCar(carData: Car): void {
    this.carService.createCar(carData).subscribe({
      next: (response) => {
         this.router.navigate(['/cars']);
      },
      error: ({ error }) => {
         console.error(error.message);
         this.snackBar.open(error.message, 'Fechar', {
          duration: 3000,
          panelClass: ['error-snackbar']
        });
      }
     });
  }

  updateCar(carId: number, carData: Car): void {
    this.carService.updateCar(carId, carData).subscribe({
      next: (response) => {
         this.router.navigate(['/cars']);
      },
      error: ({error}) => {
         console.error(error.message);
      }
     });
 }

 onSubmit(): void {
    if (this.carForm.valid) {
      const carId = this.route.snapshot.paramMap.get('id');

      carId ? this.updateCar(Number(carId), this.carForm.value) : this.createCar(this.carForm.value);
    } else {
      this.carForm.markAsTouched();
      console.log('Formulário inválido');
    }
 }
 }
