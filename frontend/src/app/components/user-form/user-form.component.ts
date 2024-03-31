import { Component } from '@angular/core';

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
        MatButtonModule
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

  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      name: [''],
      lastName: [''],
      email: ['', [Validators.required, Validators.email]],
      birthday: [''],
      phone: [''],
      login: [''],
      password: ['']
    });
 }

  onSubmit() {
    if (this.userForm.valid) {
      console.log(this.userForm.value);
      // Aqui você pode adicionar a lógica para enviar os dados do formulário para o servidor
    } else {
      console.log('Formulário inválido');
    }
 }

}
