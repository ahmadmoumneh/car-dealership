import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Special } from '../classes/special';

@Injectable({
  providedIn: 'root'
})
export class SpecialService {
  private serverUrl: string;

  constructor(private http: HttpClient) {
    this.serverUrl = 'http://localhost:8080/api/cardealership';
  }

  addSpecial(special: Special): Observable<Special> {
    return this.http.post<Special>(this.serverUrl + '/admin/addspecial', special);
  }

  deleteSpecialById(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.serverUrl + '/admin/deletespecial/' + id);
  }

  getAllSpecials() : Observable<Special[]>{
    return this.http.get<Special[]>(this.serverUrl + '/home/specials');;
  }
}
