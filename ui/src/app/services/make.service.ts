import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Make } from '../classes/make';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class MakeService {
  private serverUrl: string;

  constructor(private http: HttpClient) { 
    this.serverUrl = 'http://localhost:8080/api/cardealership';
  }

  addMake(make: Make): Observable<Make> {
    return this.http.post<Make>(this.serverUrl + '/admin/addmake', make);
  }

  getAllMakes() : Observable<Make[]>{
    return this.http.get<Make[]>(this.serverUrl + '/admin/makes');;
  }
}
