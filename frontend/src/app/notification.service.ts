import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import { Notification1 } from './dashboard/notification';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Message } from './socket/message';
import { DatePipe } from '@angular/common';
import { UserProfileService } from './user-profile.service';
//import { DashboardComponent } from './dashboard/dashboard.component';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private userprofile:UserProfileService,private toastr: ToastrService,private http: HttpClient,private datePipe: DatePipe) { }

      /* web socket starts */
      isLoaded = false;
      name:any;
      isCustomSocketOpened = false;
      private stompClient;
      messages: Notification[] = [];
      email;
      url= 'https://gamos.stackroute.io:8989/chat/api/notify';

        initializeWebSocketConnection() {
          const ws = new SockJS("https://gamos.stackroute.io:8990/socket");
          this.email = localStorage.getItem('emailid');
          this.stompClient = Stomp.over(ws);
          const that = this;
          // tslint:disable-next-line: only-arrow-functions
          this.stompClient.connect({}, function(frame) {
            that.isLoaded = true;
            that.openGlobalSocket();
          });
        }
        openGlobalSocket() {
          this.stompClient.subscribe('/socket-publisher/' + this.email, (message) => {
            this.handleResult(message);
          });
        }
        // openSocket() {
        //   if (this.isLoaded) {
        //     this.isCustomSocketOpened = true;
        //     this.stompClient.subscribe('/socket-publisher/' + 'febinthomas97@gmail.com', (message) => {
        //       this.handleResult(message);
        //     });
        //   }
        // }
        arr:any;
        arr1:any;
        sendMessageUsingSocket(clickedEmail) {
          this.userprofile.GetProfileDetails(this.email).subscribe(data=>{
            console.log(data);
            this.arr1=data;
            const message: Notification1 = { notification: 'request sent by: ' + this.arr1.basicPersonalDetails.firstName, fromId: this.email, toId: clickedEmail};
            this.stompClient.send('/socket-subscriber/send/notification', {}, JSON.stringify(message));
            console.log(message);
            this.handleResult(message);
          });
        }
          sendAcceptedMessageUsingSocket(clickedEmail) {
            this.userprofile.GetProfileDetails(this.email).subscribe(data=>{
              console.log(data);
              this.arr=data;
              const message: Notification1 = { notification: 'request Accepted by: ' + this.arr.basicPersonalDetails.firstName, fromId: this.email, toId: clickedEmail};
            const message1: Message = {message: 'request Accepted by: ' + this.arr.basicPersonalDetails.firstName, fromId: this.email, toId: clickedEmail,
            date: this.datePipe.transform(new Date(), 'MMM d, y, h:mm:ss a')};
            this.stompClient.send('/socket-subscriber/send/notification', {}, JSON.stringify(message));
            this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(message1));
            console.log(message);
            this.handleResult(message);
              
            });
            
          }


        handleResult(message) {
          if (message.body) {
            const messageResult: Notification = JSON.parse(message.body);
            console.log(messageResult);
            this.messages.push(messageResult);
            // this.dashboard.saveNotification(this.messages);
            this.toastr.success('new notification recieved', null, {
              timeOut: 3000
            });
          }
        }

        getNotifications(id) {
          const token = 'Bearer ' + localStorage.getItem('token');
          const httpOptions = {
            headers: new HttpHeaders({
              Authorization: token
            })
          };
          return this.http.get(this.url + '/notificationHistory/' + id, httpOptions);
        }
}
