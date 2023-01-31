import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Purchase } from '../classes/purchase';
@Injectable({
  providedIn: 'root'
})

export class PurchaseService {
  private serverUrl:string;

  constructor(private http: HttpClient) { 
    this.serverUrl = "http://localhost:8080/api/cardealership";
  }

  logPurchase(purchase:Purchase, id:number){
    return this.http.post<Purchase>(this.serverUrl + '/sales/' + id, purchase);
  }
}