import { Routes } from '@angular/router';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { LoginComponent } from './components/login/login.component';
import { CarsListComponent } from './components/cars-list/cars-list.component';
import { CarFormComponent } from './components/car-form/car-form.component';
import { UserFormEditComponent } from './components/user-edit-form/user-edit-form.component';

export const routes: Routes = [
  {path: '', component: UsersListComponent},
  { path: 'login', component: LoginComponent },
  { path: 'user-edit-form/:id', component: UserFormEditComponent },
  {path: 'user-form', component: UserFormComponent},
  {path: 'cars', component: CarsListComponent},
  {path: 'car-form', component: CarFormComponent},
  {path: 'car-form/:id', component: CarFormComponent},
];
