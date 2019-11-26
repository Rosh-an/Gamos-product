import { Component, OnInit } from '@angular/core';
import { trigger, keyframes, animate, transition } from '@angular/animations';
import * as kf from './keyframes';
import { DashboardServiceService } from '../dashboard-service.service';
import { UserProfileService } from '../user-profile.service';
import { Dummy } from '../dashboard/dashboard.component';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';


@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css'],
  animations: [
    trigger('cardAnimator', [
      transition('* => wobble', animate(1000, keyframes(kf.wobble))),
      transition('* => swing', animate(1000, keyframes(kf.swing))),
      transition('* => jello', animate(1000, keyframes(kf.jello))),
      transition('* => zoomOutRight', animate(1000, keyframes(kf.zoomOutRight))),
      transition('* => zoomOutLeft', animate(1000, keyframes(kf.zoomOutLeft))),
      transition('* => slideOutLeft', animate(1000, keyframes(kf.slideOutLeft))),
      transition('* => slideOutRight', animate(1000, keyframes(kf.slideOutRight))),
      transition('* => rotateOutUpRight', animate(1000, keyframes(kf.rotateOutUpRight))),
      transition('* => rotateOutUpLeft', animate(1000, keyframes(kf.rotateOutUpLeft))),

    ])
  ]
})



export class RecommendationComponent implements OnInit {




  // tslint:disable-next-line: max-line-length
  constructor(private spinner: NgxSpinnerService, private dashboardService: DashboardServiceService, private userProfileService: UserProfileService, private router: Router) { }

  card_no = 1;
  scard_no = '1';
  i = 0;
  c: number;
  profiles: any;
  recObj;
  Obj;



  url = '../../assets/pp' + this.scard_no + '.webp';
  animationState: string;

  dummy: Dummy = {
    userEmail: localStorage.getItem('emailid'),
    clickedEmail: '',
    action: ''
   };

  startAnimation(state) {
    console.log(state);
    if (!this.animationState) {
      this.animationState = state;
    }
  }

  resetAnimationState() {
    this.animationState = '';
  }

  change() {
    console.log(this.card_no);
    setTimeout(() => {
      this.changecard();
    }, 600);

  }

  changecard() {
    console.log(this.card_no);
    this.card_no = this.card_no + 1;
    console.log(this.card_no);
    this.scard_no = this.card_no.toString();
    console.log('card');
    this.url = '../../assets/pp' + this.scard_no + '.webp';
    console.log(this.url);
    this.i = this.i + 1;
  }

  dislike(email) {
    this.startAnimation('rotateOutUpLeft');
    this.dashboardService.dislikeProfile(email).subscribe(data => {
      console.log(data);
    });
    this.change();
  }

  like(email) {
    this.startAnimation('rotateOutUpRight');
    this.dashboardService.likeProfile(email).subscribe(data => {
      console.log(data);
    });
    this.change();
  }

  request(email) {
    this.startAnimation('rotateOutUpRight');
    this.dashboardService.sendRequest(email).subscribe(data => {
      console.log(data);
    });
    this.change();
  }

  ngOnInit() {


    this.userProfileService.fillProfileDetails().subscribe(data => {

      console.log(data) ;
      this.Obj = data;
      if (this.Obj.recommended_list.length > 0) {
        this.profiles = this.Obj.recommended_list;
      }
      console.log('Outside subscribe');
      if (this.profiles == null) {this.spinner.show(); }
      console.log('Spinner start');
      this.dashboardService.getDetails(this.Obj.basicPersonalDetails.gender).subscribe(data => {

        console.log(data);
        this.recObj = data;
        console.log('Inside subscribe');
        console.log('Bye spinner');

        this.spinner.hide();
        if (data.length > 0) {
          this.profiles = data;
        }
        console.log(this.profiles);
        console.log(this.recObj);
      });

    });
    if (this.Obj !== undefined) {
      console.log('Hello');



    }





  }



  click1(email) {
    console.log('clicked');
    this.dummy.action = 'entered';
    this.dummy.clickedEmail = email;
    console.log(this.dummy);
    this.dashboardService.sendForInflux(this.dummy).subscribe();
  }
  GoToShowProfile(email) {
    console.log(email);
    this.router.navigate(['show-profile'], { queryParams: { email:email } });
  }



}
