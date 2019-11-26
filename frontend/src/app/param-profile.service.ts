import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
export class ParamMessage {
  message: string;
}
@Injectable({
  providedIn: 'root'
})
export class ParamProfileService {

  constructor(private httpClient: HttpClient) { }

  send2profilemicro(string1: string) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json',
                                Authorization: 'Bearer ' + localStorage.getItem('token')
    })};
    const url = `https://gamos.stackroute.io:8989/profile/profile/search`;
    const paramMessage = new ParamMessage();
    paramMessage.message = string1;
    console.log(string1);
    return this.httpClient.post(url, paramMessage, httpOptions);
  }
}
