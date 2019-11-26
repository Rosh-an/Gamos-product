import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
export class RabbitMq {
  email: string;
  gender: string;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardServiceService {
  constructor(private http: HttpClient) { }
  getDetails(g): any {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const rabbitMq = new RabbitMq();
    rabbitMq.email = localStorage.getItem('emailid');
    rabbitMq.gender = g;
    const postUrl = 'https://gamos.stackroute.io:8989/profile/profile/getrecommendations';
    return this.http.post(postUrl, rabbitMq, httpOptions);
  }

  sendForInflux(dummy): any {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    console.log(dummy);
//     console.log(this.httpOptions);
    const postUrl = `https://gamos.stackroute.io:8989/upstream/influx`;
    // let post_url = `http://15.206.46.12:8020/influx`;
    return this.http.post(postUrl, dummy, httpOptions);
  }

  likeProfile(likedEmail) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const userObj = {
      user_email: localStorage.getItem('emailid'),
      liked_email: likedEmail
    };
    const postUrl = `https://gamos.stackroute.io:8989/profile/likeProfile/`;
    return this.http.post(postUrl, userObj, httpOptions);
  }

  dislikeProfile(dislikedEmail) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const userObj = {
      user_email: localStorage.getItem('emailid'),
      liked_email: dislikedEmail
    };
    const postUrl = `https://gamos.stackroute.io:8989/profile/dislikeProfile/`;
    return this.http.post(postUrl, userObj, httpOptions);
  }

  sendRequest(requestedEmail) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const userObj = {
      user_email: localStorage.getItem('emailid'),
      liked_email: requestedEmail
    };
    const postUrl = `https://gamos.stackroute.io:8989/profile/sendRequest/`;
    return this.http.post(postUrl, userObj, httpOptions);
  }

  addInfluxToReco() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const emailObj =
    {
      email: localStorage.getItem('emailid')
    }
    const postUrl= `https://gamos.stackroute.io:8989/profile/viewprofile/`;
    return this.http.post(postUrl, emailObj, httpOptions);

  }
  addToAcceptedRequests(senderEmail) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const userObj = {
      user_email: localStorage.getItem('emailid'),
      liked_email: senderEmail
    };
    const postUrl = `https://gamos.stackroute.io:8989/profile/acceptProfile/`;
    return this.http.post(postUrl, userObj, httpOptions);
  }

  createChatHistory(senderEmail) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('token')
      })
    };
    const userObj = {
      user_email: localStorage.getItem('emailid'),
      liked_email: senderEmail
    };
    const postUrl = `https://gamos.stackroute.io:8989/socket/api/chat/matchedRecepeint`;
    return this.http.post(postUrl, userObj, httpOptions);

  }

}
