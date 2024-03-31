import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { User } from "../models/user.model";

@Injectable({
  providedIn: 'root'
 })
 export class UserService {
  private apiUrl = 'http://localhost:3000/api/users';

  constructor(private http: HttpClient) {}

  // getUserById(id: string): Observable<any> {
  //    return this.http.get(`${this.apiUrl}/${id}`);
  // }

  getUserById(id: string): Observable<User> {
    // Exemplo de objeto mockado
    const mockUser: User = {
       id: Number(id),
       firstName: 'Nome Mockado',
       lastName: 'Sobrenome Mockado',
       email: 'email@mockado.com',
       login: 'login-mockado',
       password: 'pass',
       birthday: new Date(),
       phone: '1234'

       // Adicione outros campos conforme necessário
    };

    // Retorna um Observable que emite o objeto mockado
    return of(mockUser);
   }
 }
