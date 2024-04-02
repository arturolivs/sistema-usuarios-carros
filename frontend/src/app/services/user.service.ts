import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { User } from "../models/user.model";
import { environment } from "../../environment";

@Injectable({
  providedIn: 'root'
 })
 export class UserService {
  private apiUrl = `${environment.apiUrl}/api/users`;

  constructor(private http: HttpClient) {}

  getUserById(id: string): Observable<any> {
     return this.http.get(`${this.apiUrl}/${id}`);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  removeUser(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${userId}`);
 }

  createUser(userData: User): Observable<any> {
    return this.http.post(this.apiUrl, userData);
  }

  updateUser(userId: string, userData: User): Observable<any> {
    return this.http.put(`${this.apiUrl}/${userId}`, userData);
  }
 }
