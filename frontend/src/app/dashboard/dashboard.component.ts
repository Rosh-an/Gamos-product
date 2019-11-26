import { Component, HostListener, ViewChild, OnInit } from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { MatBottomSheet, MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { RxSpeechRecognitionService, resultList } from '@kamiazya/ngx-speech-recognition';
import { DashboardServiceService } from '../dashboard-service.service';
import { SearchService } from '../search.service';
import { FormControl } from '@angular/forms';
import { NgbCarousel, NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import { ShowProfileComponent } from '../show-profile/show-profile.component';
import { SocketComponent } from '../socket/socket.component';
import { observable, BehaviorSubject } from 'rxjs';
import { ParamProfileService } from '../param-profile.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ToastrService } from 'ngx-toastr';
import {Notification1} from './notification';

  // import as RouterEvent to avoid confusion with the DOM Event
  import{
  Event as RouterEvent,
  NavigationStart,
  NavigationEnd,
  NavigationCancel,
  NavigationError
} from '@angular/router';





import { environment } from 'src/environments/environment';
import { ApiAiClient } from 'api-ai-javascript/es6/ApiAiClient';
import { trigger, transition, animate, style, state } from '@angular/animations';
import { Router } from '@angular/router';
import { NotificationService } from '../notification.service';
import { MyvidComponent } from '../myvid/myvid.component';
import { UserProfileService } from '../user-profile.service';


export interface URL {
 url: string;

}

export class Message {
  constructor(public content: string, public sentBy: string) { }
}
export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

export interface Card {
  header: string;
  video: string;
  description: string;
}

export interface Dummy {
  userEmail: string;
  clickedEmail: string;
  action: string;
}



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  animations: [trigger('EnterLeave', [
    state('flyIn', style({ transform: 'translateX(0)' })),
    transition(':enter', [
      style({ transform: 'translateX(-100%)' }),
      animate('0.5s 300ms ease-in')
    ]),
    transition(':leave', [
      animate('0.3s ease-out', style({ transform: 'translateX(100%)' }))
    ])
  ]),
  trigger('simpleFadeAnimation', [
    state('in', style({ opacity: 1 })),
    transition(':enter', [
      style({ opacity: 0 }),
      animate(600)
    ])
  ]
  )]
})

export class DashboardComponent implements OnInit {


  /*************************
   *  CHAT MODAL
   *************************/

   openchat(){
     this.dialog.open(SocketComponent);
   }

  

  chatObj:any;



  title = 'app';
  userId = 999;
  currentTheme='dark-theme';
  ups:UserProfileService;
  

  logout() {
    this.dashboardService.addInfluxToReco().subscribe(data => {
    console.log(data);
    console.log("checking")
    });
    localStorage.clear();
  }

  /********************Notification Array ************/
  notifications:any;

  // saveNotification(messages){
  //   this.messages=messages;
  // }

   loadNotifications() {
     this.notificationService.getNotifications(localStorage.getItem('emailid')).subscribe(data => {
      console.log(data);
      this.notifications = data;

    });
   }
  /**************************************************/

  // Sets initial value to true to show loading spinner on first load
  loading = true;

  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.loading = true
    }
    if (event instanceof NavigationEnd) {
      this.loading = false
    }

    // Set loading state to false in both of the below events to hide the spinner in case a request fails
    if (event instanceof NavigationCancel) {
      this.loading = false
    }
    if (event instanceof NavigationError) {
      this.loading = false
    }
  }



  constructor(private spinner: NgxSpinnerService,private userprofileservice:UserProfileService,public dialog: MatDialog, private breakpointObserver: BreakpointObserver, public service: RxSpeechRecognitionService,
              private searchService: SearchService, private dashboardService: DashboardServiceService, public _bottomSheet: MatBottomSheet,
              private paramProfileService: ParamProfileService, private router:Router, private notificationService:NotificationService) {this.ups=this.userprofileservice;}

  //Dashboard
  connected: boolean[] = [false, false, false, false, false, false, false, false, false];
  liked: boolean[] = [false, false, false, false, false, false, false, false, false];
  likeoverlay: boolean[] = [false, false, false, false, false, false, false, false, false];
  connectoverlay: boolean[] = [false, false, false, false, false, false, false, false, false];
  closecard: boolean[] = [false, false, false, false, false, false, false, false, false];
  mute: boolean[] = [true, true, true, true, true, true, true, true, true];

  togglemute(index) {
    this.mute[index] = !this.mute[index];
  }

  heroes: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"];
  /************CONNECT AND LIKE*************** */

  connect(index, email) {
    this.connected[index] = true;
    this.connectoverlay[index] = true;
    this.closecard[index] = true;
    console.log('connectoverlay' + this.connectoverlay[index]);
    console.log('closecard' + this.closecard[index]);
    this.dashboardService.sendRequest(email).subscribe(data => {
      console.log(data);
    });
    // setTimeout(()=>{
    //   this.connectoverlay=false;
    //   console.log(this.connectoverlay);
    // },1500);

  }

  showCard(index) {
    this.connectoverlay[index] = false;
    this.closecard[index] = false;
    console.log('closecard' + this.closecard[index]);
  }

  showCard1(index) {
    this.likeoverlay[index] = false;
    this.closecard[index] = false;
    console.log('closecard' + this.closecard[index]);
  }




  // addoverlay(){
  //   console.log("Overlay called");
  //   if(this.connectoverlay==true)
  //   return 'blue';
  //   else
  //   return 'transparent';
  // }

  like(index, email) {
    this.liked[index] = true;
    this.likeoverlay[index] = true;
    this.closecard[index] = true;
    console.log("connectoverlay" + this.connectoverlay[index]);
    console.log("closecard" + this.closecard[index]);
    this.dashboardService.likeProfile(email).subscribe(data => {
      console.log(data);
    });
  }

  filter = false;
  searchresults = false;

  /**************************
        SEARCH
   **************************/
  searchstatus1 = false;

  search() {
    this.searchstatus1 = !this.searchstatus1;
  }




  /****************************
        Profile Dialog
   ****************************/

  readonly token = environment.dialogflow.angularBot;
  readonly client = new ApiAiClient({ accessToken: this.token });
  conversation = new BehaviorSubject<Message[]>([]);

  profilemodal() {
    this.dialog.open(ShowProfileComponent);

  }


  /*************************
        CAROUSEL
    **************************/


  /***********************
        CHAT
   **********************/
  openBottomSheet() :void{

    console.log("Open");
    this._bottomSheet.open(SocketComponent);
  }


  /*----------------------
          FILTER
  -----------------------*/

  panelOpenState = false;
  checked = false;
  indeterminate = false;
  labelPosition = 'after';
  labelPosition1: any;
  labelPosition2: any;
  disabled = false;

  city = new FormControl('');



  CAROUSEL_BREAKPOINT3 = 1200;
  CAROUSEL_BREAKPOINT4 = 600;


  n: string;
  ncol = 3;
  auto = false;


  @HostListener('window:resize')
  onWindowResize() {
    if (window.innerWidth >= this.CAROUSEL_BREAKPOINT4 && window.innerWidth <= this.CAROUSEL_BREAKPOINT3) {
      this.ncol = 2;
    }
    else if (window.innerWidth <= this.CAROUSEL_BREAKPOINT4) {
      this.ncol = 1;
    }
    else {
      this.ncol = 3;
    }
  }


  /*--------GRID LIST----------*/
  tiles1: Tile[] = [
    { text: 'One', cols: 3, rows: 1, color: 'lightblue' },
    { text: 'Two', cols: 1, rows: 2, color: 'lightgreen' },
    { text: 'Three', cols: 1, rows: 1, color: 'lightpink' },
    { text: 'Four', cols: 2, rows: 1, color: '#DDBDF1' },
  ];

  /*-------CARD LOST---------*/
  card: Card[] = [
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    },
    {
      header: 'Shiba Inu',
      video: '../../assets/pfv.mp4',
      description: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from JapanA small, agile dog.'
    }

  ];




  fruits: string[] = ['Apple', 'Orange', 'Banana'];




  public dashboard = {};

  /************************
          Click Profile
     *************************/


  dummy: Dummy = {
    userEmail: '',
    clickedEmail: 'eg.com',
    action: ''
  };

  /**********************************
   *
   *
   */




  isClicked = false;

  public slidesList = new Array<never>(3);

  Obj: any;
  onClick(temp, message) {
    
    console.log(message);

    console.log(message.value);
    this.isClicked = temp;
    const msg = message.value;

    const userMessage = new Message(msg, 'user');
    this.update(userMessage);

    this.client.textRequest(msg)
      .then(res => {
        
        const speech = res.result.fulfillment.speech;
        const botMessage = new Message(speech, 'bot');
        this.update(botMessage);
        console.log(botMessage.content);
        
        this.paramProfileService.send2profilemicro(botMessage.content).subscribe((result) => {
          
          // This code will be executed when the HTTP call returns successfully
          console.log(result);
          this.Obj = result;
          console.log(this.Obj.imageUrl[0]);
          this.SendToSearch(result);
          
        });
        
        
      });
      
  }
  update(msg: Message) {
    this.conversation.next([msg]);
  }
  SearchFilter(city) {
    this.onClick(true, { value: this.labelPosition + " , aged " + this.labelPosition1 + " who is " + this.labelPosition2 + " and lives in " + city });
  }
  SendToSearch(obj) {
    this.searchService.setSentObj(obj);
  }
  GoToShowProfile(email) {
    console.log(email);
    let url='show-profile'+'?email='+email;
    // window.open(url,"_blank");
    // this.router.navigate(['show-profile'], { queryParams: { email: email } }).then(result => {  window.open(url, '_blank'); });
    this.router.navigate(['show-profile'], { queryParams: { email: email },skipLocationChange:true });
    
  }

 



  // openBottomSheet(): void {
  //   this._bottomSheet.open(BottomSheetOverviewExampleSheet);
  // }
  message = '';





  /*--------GRID LIST----------*/

hide;

  hidedashboard(){
    this.hide=true;
  }
  // tslint:disable-next-line: use-lifecycle-interface
  ngOnInit() {
    this.hide=false;
    console.log("bring chat");
    this.userprofileservice.fillProfileDetails().subscribe(
      data => {this.chatObj=data; console.log(this.chatObj);}
    );

    
    

      /* web Socket */


      this.notificationService.initializeWebSocketConnection();


        /******************************************
     *  web socket ends
     * ***************************************/

  }
  openProfile() {
      // this.dashboardService.sendForInflux(this.dummy).subscribe();
  }




  listen() {
    //  alert("listening");
    try {
      this.service
        .listen()
        .pipe(resultList)
        .subscribe((list: SpeechRecognitionResultList) => {
          this.message = list.item(0).item(0).transcript;
          console.log('Result: ', this.message, list);
        });
    }
    catch (error) {
      console.log("Unable to listen. Please try again")
    }
  }


  /************************
        PLAY VIDEO
   ************************/

   openprofilemodal(){
     this.dialog.open(ShowProfileComponent);
   }

   playvideo(url){
     console.log("video");
     this.dialog.open(MyvidComponent,{data: url});
   }

   closevideo(){
     console.log("video stop");
     this.dialog.closeAll();
   }

   play(){
     console.log("on");
   }

   out(){
     console.log("out");
   }

   click1(email) {
    console.log('clicked');
    this.dummy.action = 'entered';
    this.dummy.userEmail=localStorage.getItem('emailid');
    this.dummy.clickedEmail = email;
    console.log(this.dummy);
    this.dashboardService.sendForInflux(this.dummy).subscribe();
  }
  //Send to accepted
  accepted = false;
  AddToAcceptedRequest(senderEmail){
    console.log(senderEmail);

    this.dashboardService.addToAcceptedRequests(senderEmail).subscribe(data=>{
      console.log(data);
      this.accepted = true;
    });

    this.notificationService.sendAcceptedMessageUsingSocket(senderEmail);

  }



  /****************************
       DASHBOARD PROFILES
   ****************************/




  //  opensocket(){
  //    this.notificationService.sendMessageUsingSocket("srishty@gmail.com");
  //  }
  //  getprofile() {
  //   this.dashboardService
  //   .getDetails()
  //   .subscribe((data) => {
  //     console.log(data);
  //   });
  //  }
}
