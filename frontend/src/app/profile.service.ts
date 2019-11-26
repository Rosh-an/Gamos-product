import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { personal } from './personal';
@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }
  addPersonal(per: personal): any {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: token
      })
    };
    console.log(per);

    console.log(httpOptions);
    const personalObj = {
      basicPersonalDetails: per.basic,
      habitsDetails: per.habits,
      familyDetails: per.family
    };
    // tslint:disable-next-line: no-string-literal
    delete personalObj.basicPersonalDetails['email'];
    const sendObj = {
      email: localStorage.getItem('emailid'),
      personal: personalObj
    };
    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/upstream/personal`;
    // let post_url = `http://15.206.46.12:8020/personal`;
    return this.http.post(post_url, sendObj, httpOptions);
  }

  addEducation(prof, edu): any {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
       headers: new HttpHeaders({
         'Content-Type':  'application/json',
         Authorization: token
       })
     };
    console.log(edu);
    const profObj = {
      professional: prof,
      education: edu
    };
    const sendObj = {
      email: localStorage.getItem('emailid'),
      professionalObject: profObj
    };
    console.log(sendObj);

    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/upstream/professional`;
    return this.http.post(post_url, sendObj, httpOptions);
  }

  addHoroscope(hor): any {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
       headers: new HttpHeaders({
         'Content-Type':  'application/json',
         Authorization: token
       })
     };
    console.log(hor);
    const sendObj = {
      email: localStorage.getItem('emailid'),
      horoscopedata: hor
    };
    // let post_url = `http://localhost:3330/posts`;
    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/upstream/horoscope`;
    return this.http.post(post_url, sendObj, httpOptions);
  }
  SentObjBackEnd(Obj) {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
       headers: new HttpHeaders({
         'Content-Type':  'application/json',
         Authorization: token
       })
     };
    // tslint:disable-next-line: variable-name
    const post_url = `https://gamos.stackroute.io:8989/profile/profile/`;
    // let httpOptions = {
    //   headers: new HttpHeaders({
    //     'Content-Type':  'application/json'
    //   })
    // };
    Obj.basicPersonalDetails.email = localStorage.getItem('emailid');
    Obj.hasPersonalDetails = true;
    console.log(Obj);

    return this.http.post(post_url, Obj, httpOptions);
  }
}
