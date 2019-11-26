import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {

  constructor(private http: HttpClient) { }
  Login(userObj): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/auth/login`;
    console.log(userObj.userEmail, 'Inside Service Email');
    return this.http.post(post_url, userObj, httpOptions);
  }
  Register(userObj): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/auth/register`;
    console.log(userObj.userEmail, 'Inside Service Email');
    return this.http.post(post_url, userObj, httpOptions);
  }
}
