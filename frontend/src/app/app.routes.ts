import { Routes } from '@angular/router';
import { UsersListComponent } from './components/users-list/users-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';

export const routes: Routes = [
  { path: 'user-form/:id', component: UserFormComponent },
  {path: 'user-form', component: UserFormComponent},
  {path: '', component: UsersListComponent},
];
