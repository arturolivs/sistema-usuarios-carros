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
import { MatListModule } from '@angular/material/list';

import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
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
  selector: 'app-user-edit-form',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: imports,
  templateUrl: './user-edit-form.component.html',
  styleUrl: './user-edit-form.component.scss'
})
export class UserFormEditComponent {
  userForm: FormGroup;
  matcher = new MyErrorStateMatcher();

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
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
     });

 }

 ngOnInit(): void {
  const userId = this.route.snapshot.paramMap.get('id');

  if (userId) {
    this.loadUserData(userId);
  }
 }

 loadUserData(userId: string): void {
  this.userService.getUserById(userId).subscribe(user => {
     this.userForm.setValue({
       firstName: user.firstName,
       lastName: user.lastName,
       email: user.email,
       birthday: new Date(user.birthday),
       phone: user.phone,
       login: user.login,
       password: user.password
     });
  });
 }


 onSubmit(): void {
  if (this.userForm.valid) {
    const userId = this.route.snapshot.paramMap.get('id');
  if (userId) {
    this.updateUser(userId, this.userForm.value);

  }
  } else {
    this.userForm.markAsTouched();
    console.log('Formulário inválido');
  }
}

updateUser(userId: string, userData: User): void {
  this.userService.updateUser(userId, userData).subscribe({
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

}
