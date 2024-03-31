import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';

import { UserFormComponent } from './components/user-form/user-form.component';


const imports = [
  RouterOutlet,
  MatIconModule,
  MatSidenavModule,
  MatButtonModule,
  UserFormComponent,
]

@Component({
  selector: 'app-root',
  standalone: true,
  imports: imports,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {
  title = 'frontend';
  showFiller = true;


}
