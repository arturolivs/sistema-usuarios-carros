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
} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatButtonModule} from '@angular/material/button';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

const imports = [
        FormsModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatButtonModule,
        RouterModule
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
  title: string = '';

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute) {

    this.userForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: ['', [Validators.required, Validators.email]],
      birthday: [''],
      phone: [''],
      login: [''],
      password: ['']
    });
 }

 ngOnInit(): void {
  const userId = this.route.snapshot.paramMap.get('id');
  if (userId) {
    this.title = 'Alterar Usuário';
    this.loadUserData(userId);
  } else {
    this.title = 'Cadastro de Usuário';
     this.initializeFormForNewUser();
  }
 }


 loadUserData(userId: string): void {
  this.userService.getUserById(userId).subscribe(user => {
     this.userForm.setValue({
       firstName: user.firstName,
       lastName: user.lastName,
       email: user.email,
       birthday: user.birthday,
       phone: user.phone,
       login: user.login,
       password: user.password
     });
  });
 }

 initializeFormForNewUser(): void {
  this.userForm = this.fb.group({
    firstName: [''],
    lastName: [''],
    email: ['', [Validators.required, Validators.email]],
    birthday: [''],
    phone: [''],
    login: [''],
    password: ['']
  });
 }

 onSubmit(): void {
  if (this.userForm.valid) {
    const userId = this.route.snapshot.paramMap.get('id');
    if (userId) {
      this.updateUser(userId, this.userForm.value);
    } else {
      this.createUser(this.userForm.value);
    }
  } else {
    console.log('Formulário inválido');
  }
 }


 createUser(userData: User): void {
    console.log(userData);
  }

  updateUser(userId: string, userData: User): void {
    console.log(userId, userData);
  }

}
