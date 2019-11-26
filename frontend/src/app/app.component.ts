import { Component , HostBinding, NgModule, HostListener, AfterViewInit, Input, OnInit} from '@angular/core';
import { Event as NavigationEvent, NavigationEnd } from "@angular/router";
import { filter } from "rxjs/operators";
import { NavigationStart } from "@angular/router";
import { Router, NavigationError, NavigationCancel, RoutesRecognized } from '@angular/router'


import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';
import { DialogueOverviewComponent } from './dialogue-overview/dialogue-overview.component';
import { fromEvent } from 'rxjs';
import { LoginComponent } from './login/login.component';
import { ShowProfileComponent } from './show-profile/show-profile.component';
import { Dummy } from './dashboard/dashboard.component';
import { DashboardServiceService } from './dashboard-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})




export class AppComponent implements OnInit {
  
  
  

  // I initialize the app component.
  constructor(private router: Router, private dashboardService: DashboardServiceService ) {



    console.log('***********MESSAGE START************');





    router.events
        .pipe(
            // The "events" stream contains all the navigation events. For this demo,
            // though, we only care about the NavigationStart event as it contains
            // information about what initiated the navigation sequence.
            filter(
                ( event: NavigationEvent ) => {

                    return( event instanceof NavigationStart );

                }

            )
        )
        .subscribe(
            ( event: NavigationStart ) => {

                console.group( 'NavigationStart Event' );
                // Every navigation sequence is given a unique ID. Even "popstate"
                // navigations are really just "roll forward" navigations that get
                // a new, unique ID.
                console.log( 'navigation id:', event.id );
                console.log( 'route:', event.url );
                console.log(this.checknavigation);
                if (this.checknavigation === true) {
                  this.checknavigation = false;
                  this.first = false;
                  console.log('Exited');
                  console.log(this.email);
                  this.dummy.clickedEmail = this.email;
                  this.dummy.action = 'exited';
                  this.dummy.userEmail = localStorage.getItem('emailid');
                  this.dashboardService.sendForInflux(this.dummy).subscribe();
                }
                console.log(this.checknavigation);
                if ((event.url.startsWith('/#/show-profile?') || event.url.startsWith('/show-profile?'))  && this.checknavigation === false && this.first === false) {
                  this.checknavigation = true;
                  this.first = true;
                  console.log('Entered');
                  console.log(this.email);
                  this.dummy.clickedEmail = this.email;
                  this.dummy.action = 'entered';
                  this.dummy.userEmail = localStorage.getItem('emailid');
                  // this.dashboardService.sendForInflux(this.dummy).subscribe();
                }
                // The "navigationTrigger" will be one of:
                // --
                // - imperative (ie, user clicked a link).
                // - popstate (ie, browser controlled change such as Back button).
                // - hashchange
                // --
                // NOTE: I am not sure what triggers the "hashchange" type.
                console.log( 'trigger:', event.navigationTrigger );






                // This "restoredState" property is defined when the navigation
                // event is triggered by a "popstate" event (ex, back / forward
                // buttons). It will contain the ID of the earlier navigation event
                // to which the browser is returning.
                // --
                // CAUTION: This ID may not be part of the current page rendering.
                // This value is pulled out of the browser; and, may exist across
                // page refreshes.
                if ( event.restoredState ) {

                    console.warn(
                        'restoring navigation id:',
                        event.restoredState.navigationId
                    );

                }

                console.groupEnd();
            }

        );

          }
  checknavigation: boolean;
  email: string;
  first = false;

  dummy: Dummy = {
    userEmail: 'example.com',
    clickedEmail: 'eg.com',
    action: ''
   };


  checknavigationtoggle() {
    this.checknavigation = true;
  }

  giveemail(email) {
    this.email = email;
  }




  ngOnInit() {
    this.checknavigation = false;
    console.log('Refresh');
  }
}



