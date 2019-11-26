import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserProfileService } from '../user-profile.service';
import { Location } from '@angular/common';
import { AppComponent } from '../app.component';
import { NotificationService } from '../notification.service';
import { DashboardServiceService } from '../dashboard-service.service';



@Component({
  selector: 'app-show-profile',
  templateUrl: './show-profile.component.html',
  styleUrls: ['./show-profile.component.css']
})
export class ShowProfileComponent implements OnInit {

  subs:boolean;
  
  constructor(private app: AppComponent, private location: Location, private router: ActivatedRoute, private userProfileService: UserProfileService, private notificationService: NotificationService, private dashboardService: DashboardServiceService) { }
  email;
  showProfileObj: any;
  basicPersonalDetails: any;
  professionalDetails: any;
  educationalDetails: any;
  habitsDetails: any;
  familyDetails: any;
  horoscopeDetails: any;
  step = 0;
  spin=false;


  subscribe(){
    
    console.log(this.subs);
    // this.blur="blur(0px)";
    this.spin=true;
    setTimeout(() => {
      this.spin=false;
      this.subs=true;
    }, 2000);

  }
  hasRequested = false;
  hasAccepted = false;

  back() {
    // this.location.back();
    console.log('back');
    
  }

  logout() {
    localStorage.clear();
  }

  likedby:any;
  connected:any;

  ngOnInit() {
    this.subs=false;
    console.log("subs"+this.subs);
    this.router.queryParams.subscribe(
      data => {
        console.log(data);
        this.app.giveemail(data.email);
        this.email = data.email;
        this.userProfileService.GetProfileDetails(this.email).subscribe(data => {
          this.showProfileObj = data;
          this.basicPersonalDetails = this.showProfileObj.basicPersonalDetails;
          if(localStorage.getItem('emailid')=='vishnu@gmail.com'){
            this.subs=true;
          }

          this.habitsDetails = this.showProfileObj.habitsDetails;
          this.familyDetails = this.showProfileObj.familyDetails;
          this.professionalDetails = this.showProfileObj.professionalDetails;
          this.educationalDetails = this.showProfileObj.educationalDetails;
          this.horoscopeDetails = this.showProfileObj.horoscopeDetails;
          this.horoscopeDetails.dateOfBirth=this.horoscopeDetails.dateOfBirth.substring(0,10);
          console.log(data);
          this.likedby=this.showProfileObj.liked_by_profiles.length;
          this.connected=this.showProfileObj.accepted_requests.length;
          if (this.showProfileObj.received_requests.indexOf(localStorage.getItem('emailid')) > -1) {
            this.hasRequested = true;
          }
          else if ( this.showProfileObj.accepted_requests.indexOf(localStorage.getItem('emailid')) > -1){
            this.hasAccepted = true;
          }
        });
      }
    );
    this.notificationService.initializeWebSocketConnection();
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }
  Connect(email) {
    console.log(email);
    this.notificationService.sendMessageUsingSocket(email);
    this.dashboardService.sendRequest(email).subscribe();
    this.hasRequested = true;
  }

}
