import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contact } from '../classes/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private serverUrl: string;

  constructor(private http: HttpClient) {
    this.serverUrl = 'http://localhost:8080/api/cardealership';
  }

  addContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.serverUrl + '/home/addcontact', contact);
  }
}