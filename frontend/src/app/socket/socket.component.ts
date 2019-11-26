import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ChatAdapter } from 'ng-chat';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { environment } from '../../environments/environment';
import { SocketService } from './socket.service';
import { ToastrService } from 'ngx-toastr';
import { Message } from './message';
import { DatePipe } from '@angular/common';
import { object } from 'prop-types';
import { MatBottomSheetRef } from '@angular/material';
import { HttpHeaders } from '@angular/common/http';
import { UserProfileService } from '../user-profile.service';


@Component({
  selector: 'app-socket',
  templateUrl: './socket.component.html',
  styleUrls: ['./socket.component.css'],
  providers: [DatePipe]
})
export class SocketComponent implements OnInit {

  email=localStorage.getItem('emailid');

  isLoaded = false;
  isCustomSocketOpened = false;
  private stompClient;
  form: FormGroup;
  userForm: FormGroup;
  messages: Message[] = [];
  chatHistory: any;
  chatStatus = true;
  selectedConversation = false;
  chats: any;
  retrievedMsgs: any = [];
  senderDetail;

  constructor(private userprofileservice: UserProfileService,private socketService: SocketService, private toastr: ToastrService, private datePipe: DatePipe
  ) { }

  /*************************
        CHAT WINDOW
   *************************/

    chatnames:any=[];

  ngOnInit() {



    this.form = new FormGroup({
      message: new FormControl(null, [Validators.required]),
    });
    this.userForm = new FormGroup({
      fromId: new FormControl(this.email, [Validators.required]),
      toId: new FormControl(null),
    });
    this.initializeWebSocketConnection();
    
    
  }

  initializeWebSocketConnection() {
    const ws = new SockJS("https://gamos.stackroute.io:8990/socket");
    this.stompClient = Stomp.over(ws);
    const that = this;
    const token = 'Bearer ' + localStorage.getItem('token');
    //     const httpOptions = {
    //        headers: new HttpHeaders({
    //          Authorization: token,
    //           'ngsw-bypass': 'true'
    //        }),
    //      };
    // tslint:disable-next-line: only-arrow-functions
    this.stompClient.connect({}, function(frame) {
      that.isLoaded = true;
      that.openGlobalSocket();
      that.openSocket();
    });
  }

  openGlobalSocket() {
    this.stompClient.subscribe('/socket-publisher', (message) => {
      this.handleResult(message);
    });
  }

  sendMessageUsingSocket() {
    if (this.form.valid) {
      const message: Message = {
        message: this.form.value.message, fromId: this.userForm.value.fromId, toId: this.userForm.value.toId, 
        date: this.datePipe.transform(new Date(), 'MMM d, y, h:mm:ss a')
      };
      if (this.userForm.value.fromId === this.senderDetail) {
        this.printFormVal(message);
      }
      
        this.userprofileservice.GetProfileDetails(this.userForm.value.toId).subscribe(data=>{
          console.log(data);
          
          
        });

      this.stompClient.send('/socket-subscriber/send/message', {}, JSON.stringify(message));
      console.log(message);
      this.handleResult(message);
      this.showChatHistory(this.userForm.value.fromId);
      this.findChatHistory(this.userForm.value.fromId);
    }

  }

  openSocket() {
    if (this.isLoaded) {
      this.showChatHistory(this.userForm.value.fromId);
      this.chatStatus = false;
      this.isCustomSocketOpened = true;
      this.stompClient.subscribe('/socket-publisher/' + this.userForm.value.fromId, (message) => {
        this.handleResult(message);
      });
      this.findChatHistory(this.userForm.value.fromId);
    }
  }

  printFormVal(message) {
    this.messages.push(message);
  }

  handleResult(message) {
    if (message.body) {
      const messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);
      this.toastr.success('new message recieved', null, {
        timeOut: 3000
      });
    }
  }

  res1:any;
  temp:any;
  temp1:any;
  
  showChatHistory(id) {
    this.socketService.getChat(id).subscribe(res => {
      console.log(res);
      this.res1=res;
      
    //   for(let i=0;i<this.chats.length;i++)
    //   {
    //   this.userprofileservice.GetProfileDetails(this.res1.chatHistory[i].id).subscribe(data => console.log(data));
      
    //  }
    // console.log(this.chatnames);
      this.chatHistory = res;
      console.log(this.chatHistory);
      for(let i=0;i<this.chatHistory.chatHistory.length;i++)
      {
        console.log("Loop");
      this.userprofileservice.GetProfileDetails(this.chatHistory.chatHistory[i].id).subscribe(data =>
         {
           console.log(data);
          this.temp=data;
          console.log(this.temp);
          console.log(this.temp.basicPersonalDetails.firstName);
          this.temp1=this.temp.basicPersonalDetails.firstName;
          console.log(this.temp1);
          this.chatnames[i]=(this.temp1);
          console.log(this.chatnames[i]);
        });
        
      }
    // console.log(this.chatnames);
      this.findChatHistory(this.userForm.value.fromId);
      });
    //   for(let i=0;i<this.chats.length;i++)
    //   {
    //   this.userprofileservice.GetProfileDetails(this.res1.chatHistory[i].id).subscribe(data => console.log(data));
      
    //   }
    // console.log(this.chatnames);
  }

  findChatHistory(fromId) {
    // tslint:disable-next-line: prefer-for-of
    // for (let i = 0; i < this.chatHistory.length ; i++) {
    //   if (this.chatHistory[i].profileName === fromId) {
    //       this.chats = this.chatHistory[i].chatHistory;
    //   }
    // }
    this.chats = this.chatHistory.chatHistory;

      
    
    
  }

  insidechat:string;

  openChat(toId,name) {
    console.log(name);
    this.messages = [];
    this.insidechat=name;
    this.userForm.value.toId = toId;
    this.selectedConversation = true;
    this.showChatHistory(this.userForm.value.fromId);
    this.retrieveChatHistory();
  }

  retrieveChatHistory() {
    this.senderDetail = this.userForm.value.fromId;
    this.retrievedMsgs = [];
    // tslint:disable-next-line: prefer-for-of
    // for (let i = 0; i < this.chatHistory.length ; i++) {
    //   if (this.chatHistory[i].profileName === this.userForm.value.fromId) {
    //     // tslint:disable-next-line: prefer-for-of
    //     for (let j = 0; j < this.chatHistory[i].chatHistory.length; j++) {
    //       if (this.chatHistory[i].chatHistory[j].id === this.userForm.value.toId) {
    //       this.retrievedMsgs = this.chatHistory[i].chatHistory[j].message;
    //       }
    //     }
    //   }
    // }

    // tslint:disable-next-line: prefer-for-of
    for (let j = 0; j < this.chatHistory.chatHistory.length; j++) {
      if (this.chatHistory.chatHistory[j].id === this.userForm.value.toId) {
        this.retrievedMsgs = this.chatHistory.chatHistory[j].message;
      }
    }
    console.log(this.retrievedMsgs);
    this.showChatHistory(this.userForm.value.fromId);
  }
}
