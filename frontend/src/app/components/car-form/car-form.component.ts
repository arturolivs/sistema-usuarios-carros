import { Component } from '@angular/core';

import { FormBuilder,
          FormGroup,
          Validators,
          FormsModule,
          ReactiveFormsModule, } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Car } from '../../models/car.model';

import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-car-form',
  standalone: true,
  imports: [MatInputModule,
    MatFormFieldModule,
     MatButtonModule,
     FormsModule,
    ReactiveFormsModule],
  templateUrl: './car-form.component.html',
  styleUrl: './car-form.component.scss'
})
export class CarFormComponent {
  carForm: FormGroup;
  title: string = 'Cadastro de Carro';

  constructor(
     private fb: FormBuilder,
     private carService: CarService,
     private route: ActivatedRoute,
     private router: Router
  ) {
     this.carForm = this.fb.group({
       year: ['', Validators.required],
       licensePlate: ['', Validators.required],
       model: ['', Validators.required],
       color: ['', Validators.required]
     });
  }

  onSubmit(): void {
     if (this.carForm.valid) {
      this.createCar(this.carForm.value);
     } else {
       this.carForm.markAsTouched();
       console.log('Formulário inválido');
     }
  }

  createCar(carData: Car): void {
    this.carService.createCar(carData).subscribe(response => {
      console.log('Carro criado com sucesso', response);
      this.router.navigate(['/cars']);
    }, error => {
      console.error('Erro ao criar carro', error);
    });
  }
 }
