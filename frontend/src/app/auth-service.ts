import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class AuthService  {

  constructor() { }


  public isAuthenticated(): boolean {
    const token = localStorage.getItem('emailid');
    // Check whether the token is expired and return
    // true or false
    // return !this.jwtHelper.isTokenExpired(token);
    if (token == null) {
    return false;
    } else {
    return true;
    }
  }
}
