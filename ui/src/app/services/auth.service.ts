import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  isLoggedIn() {

    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');

    if (username && password) {
      return true;
    }

    

  }


}
