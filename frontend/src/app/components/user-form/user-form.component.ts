import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule,
  FormGroup,
  FormBuilder,
  FormArray,
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatButtonModule} from '@angular/material/button';
import { MatListModule } from '@angular/material/list';

import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { Car } from '../../models/car.model';
import { MatSnackBar } from '@angular/material/snack-bar';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

const imports = [
        CommonModule,
        FormsModule,
        RouterModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatDatepickerModule,
        MatButtonModule,
        MatListModule,
    ]

@Component({
  selector: 'app-user-form',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: imports,
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss'
})
export class UserFormComponent {
  userForm: FormGroup;
  matcher = new MyErrorStateMatcher();

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar) {

    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      birthday: ['', Validators.required],
      phone: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      cars: this.fb.array([
         this.fb.group({
           year: ['', Validators.required],
           licensePlate: ['', Validators.required],
           model: ['', Validators.required],
           color: ['', Validators.required]
         })
       ])
     });

 }

 onSubmit(): void {
  if (this.userForm.valid) {
    this.createUser(this.userForm.value);
  } else {
    this.userForm.markAsTouched();
    console.log('Formulário inválido');
  }
}

createUser(userData: User): void {
  this.userService.createUser(userData).subscribe({
    next: (response) => {
       this.router.navigate(['/']);
    },
    error: ({ error }) => {
       this.snackBar.open(error.message, 'Fechar', {
        duration: 3000,
        panelClass: ['error-snackbar']
      });
    }
   });
}

get cars(): FormArray {
  return this.userForm.get('cars') as FormArray;
 }

 addCar(): void {
  this.cars.push(this.fb.group({
     year: [''],
     licensePlate: [''],
     model: [''],
     color: ['']
  }));
 }

 removeCar(index: number): void {
  this.cars.removeAt(index);
 }

}
