// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { SignIn } from '../models/signin.model';

@Injectable({
 providedIn: 'root'
})
export class AuthService {
 private tokenSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
 public token: Observable<string> = this.tokenSubject.asObservable();
 private apiUrl = 'http://localhost:8080/api/signin';

 constructor(private http: HttpClient) {}

 signIn(user: SignIn): Observable<any> {
    return this.http.post(this.apiUrl, user);
 }

 setToken(token: string): void {
    console.log('token', token);
    this.tokenSubject.next(token);
 }
}
