
 import { HttpClient, HttpHeaders } from '@angular/common/http';
 import { Observable, switchMap } from 'rxjs';
import { Car } from '../models/car.model';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';

 @Injectable({
  providedIn: 'root'
 })
 export class CarService {
  private apiUrl = 'http://localhost:8080/api/cars';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getAllCars(): Observable<Car[]> {
    return this.authService.token.pipe(
      switchMap(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
        return this.http.get<Car[]>(this.apiUrl, { headers });
      })
    );
  }

  createCar(car: Car): Observable<any> {
    return this.authService.token.pipe(
      switchMap(token => {
        const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
        return this.http.post<Car>(this.apiUrl, car, { headers });
      })
    );
  }

}
