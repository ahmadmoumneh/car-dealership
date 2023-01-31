import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Model } from '../classes/model';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  private serverUrl: string;

  constructor(private http: HttpClient) { 
    this.serverUrl = 'http://localhost:8080/api/cardealership';
  }

  addModel(model: Model): Observable<Model> {
    return this.http.post<Model>(this.serverUrl + '/admin/addmodel', model);
  }

  getAllModels() : Observable<Model[]>{
    return this.http.get<Model[]>(this.serverUrl + '/admin/models');
  }
}