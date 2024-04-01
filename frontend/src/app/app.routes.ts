import { Routes } from '@angular/router';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-form/:id', component: UserFormComponent },
  {path: 'user-form', component: UserFormComponent},
  {path: '', component: UsersListComponent},
];
