import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  isLoggedIn() {

console.log(localStorage.getItem('username'));
console.log(localStorage.getItem('password'));
return true;

    // const payload = atob(token.split(".")[1]); // decode payload of token

    // const parsedPayload = JSON.parse(payload); // convert payload into an Object

    // return parsedPayload.exp > Date.now() / 1000; // check if token is expired

  }


}
