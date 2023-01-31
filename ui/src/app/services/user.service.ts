import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private serverUrl: string;

  constructor(private http: HttpClient) { 
    this.serverUrl = 'http://localhost:8080/api/cardealership';
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.serverUrl + '/admin/adduser', user);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.serverUrl + '/admin/users');
  }
}
