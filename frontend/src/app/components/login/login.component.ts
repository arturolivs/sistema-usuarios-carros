import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { SignIn } from '../../models/signin.model';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
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
           console.error('Erro:', error.message);
        },
       });
     }
  }
 }
