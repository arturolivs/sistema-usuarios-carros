import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { SignIn } from '../../models/signin.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private snackBar: MatSnackBar) {
     this.loginForm = this.fb.group({
       login: ['', Validators.required],
       password: ['', Validators.required]
     });
  }

  onSubmit(): void {
     if (this.loginForm.valid) {
       const user: SignIn = this.loginForm.value;

       this.authService.signIn(user).subscribe({
        next: (value) => {
          this.authService.setToken(value.token);
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
 }
