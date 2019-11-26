import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

import { ApiAiClient } from 'api-ai-javascript/es6/ApiAiClient';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
export class Message {
  constructor(public content: string, public sentBy: string) { }
}


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  readonly token = environment.dialogflow.videoBot;
  readonly client = new ApiAiClient({ accessToken: this.token });

  conversation = new BehaviorSubject<Message[]>([]);

  constructor(private http: HttpClient) { }

  // Sends and receives messages via DialogFlow
  converse(msg: string) {
    const userMessage = new Message(msg, 'user');
    this.update(userMessage);

    return this.client.textRequest(msg)
      .then(res => {
        const speech = res.result.fulfillment.speech;
        const botMessage = new Message(speech, 'bot');
        this.update(botMessage);
      });
  }

  // Adds message to source
  update(msg: Message) {
    this.conversation.next([msg]);
  }
  saveVideo(userObj): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({ Authorization: 'Bearer ' + localStorage.getItem('token') }),
    };
    const postUrl = `https://gamos.stackroute.io:8989/upstream/storage/video`;
    return this.http.post(postUrl, userObj, httpOptions);
  }
  postVideoBot(userObj): Observable<any> {
    const token = 'Bearer ' + localStorage.getItem('token');
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + localStorage.getItem('token')
      }),
    };
    console.log(httpOptions);
    const postUrl = `https://gamos.stackroute.io:8989/upstream/video-bot`;
    return this.http.post(postUrl, userObj, httpOptions);
  }

}
