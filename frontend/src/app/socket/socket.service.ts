import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Message } from './message';

@Injectable()
export class SocketService {
  url: string = environment.url + 'api/chat';

  constructor(private http: HttpClient) { }

  getChat(id) {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: token
      })
    };
    return this.http.get(this.url + '/chatHistory/' + id, httpOptions);

  }
}
