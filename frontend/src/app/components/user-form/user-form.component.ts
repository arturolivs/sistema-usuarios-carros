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
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  title: string = 'Cadastro de Usuário';

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {



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

 ngOnInit(): void {
  const userId = this.route.snapshot.paramMap.get('id');

  if (userId) {
    this.title = 'Alterar Usuário';
    this.loadUserData(userId);
  }
 }

 loadUserData(userId: string): void {
  this.userService.getUserById(userId).subscribe(user => {
     // Limpe o FormArray cars
     this.cars.clear();

     // Preencha o userForm com os dados do usuário
     this.userForm.setValue({
       firstName: user.firstName,
       lastName: user.lastName,
       email: user.email,
       birthday: new Date(user.birthday),
       phone: user.phone,
       login: user.login,
       password: user.password
     });

     // Preencha o FormArray cars com instâncias de FormGroup para cada carro
     user.cars.forEach((car: Car) => {
       this.cars.push(this.fb.group({
         year: car.year,
         licensePlate: car.licensePlate,
         model: car.model,
         color: car.color
       }));
     });
  });
 }


 onSubmit(): void {
  if (this.userForm.valid) {
    const userId = this.route.snapshot.paramMap.get('id');

    userId ? this.updateUser(userId, this.userForm.value) : this.createUser(this.userForm.value);
  } else {
    this.userForm.markAsTouched();
    console.log('Formulário inválido');
  }
}

createUser(userData: User): void {
  this.userService.createUser(userData).subscribe(response => {
    console.log('Usuário criado com sucesso', response);
    this.router.navigate(['/']);
  }, error => {
    console.error('Erro ao criar usuário', error);
  });
}

updateUser(userId: string, userData: User): void {
  console.log(userData);
  // this.userService.updateUser(userId, userData).subscribe(response => {
  //   console.log('Usuário atualizado com sucesso', response);
  //   this.router.navigate(['/']);
  // }, error => {
  //   console.error('Erro ao atualizar usuário', error);
  // });
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
