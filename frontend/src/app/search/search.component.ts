import { Component, OnInit } from '@angular/core';
import { trigger, transition, animate, style, state } from '@angular/animations';
import { SearchService } from '../search.service';




@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  animations: [
    // trigger(
    //   'enterAnimation', [
    //     transition(':enter', [
    //       style({transform: 'translateY(100%)', opacity: 0}),
    //       animate('500ms', style({transform: 'translateY(0)', opacity: 1}))
    //     ]),
    //     transition(':leave', [
    //       style({transform: 'translateY(0)', opacity: 1}),
    //       animate('500ms', style({transform: 'translateX(100%)', opacity: 0}))
    //     ])
    //   ]
    // )
    trigger('simpleFadeAnimation', [

      // the "in" style determines the "resting" state of the element when it is visible.
      state('in', style({opacity: 1})),

      // fade in when created. this could also be written as transition('void => *')
      transition(':enter', [
        style({opacity: 0}),
        animate(600 )
      ])])

      // fade out when destroyed. this could also be written as transition('void => *')
    //   transition(':leave',
    //     animate(600, style({opacity: 0})))
    // ])
  ]

})


export class SearchComponent implements OnInit {
  constructor(private searchService: SearchService) { }

  connected: boolean[] = [false, false, false, false, false, false, false, false, false];
  liked: boolean[] = [false, false, false, false, false, false, false, false, false];
  likeoverlay: boolean[] = [false, false, false, false, false, false, false, false, false];
  connectoverlay: boolean[] = [false, false, false, false, false, false, false, false, false];
  closecard: boolean[] = [false, false, false, false, false, false, false, false, false];
  mute: boolean[] = [true, true, true, true, true, true, true, true, true];


  // connected=false;
  // liked=false;
  // likeoverlay=false;
  // connectoverlay=false;
  // closecard=false;


  heroes: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'];
  searchObj;

  togglemute(index) {
    this.mute[index] = !this.mute[index];
  }

  ngOnInit() {
    console.log('Hello from search');
    this.searchService.getObj().subsribe(data => {
      console.log(data);
      this.searchObj = data;
    });
  }

  /************CONNECT AND LIKE*************** */

  connect(index) {
    this.connected[index] = true;
    this.connectoverlay[index] = true;
    this.closecard[index] = true;
    console.log('connectoverlay' + this.connectoverlay[index]);
    console.log('closecard' + this.closecard[index]);
    // setTimeout(()=>{
    //   this.connectoverlay=false;
    //   console.log(this.connectoverlay);
    // },1500);

  }

  showCard(index) {
    console.log(this.searchObj);

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

  like(index) {
    this.liked[index] = true;
    this.likeoverlay[index] = true;
    this.closecard[index] = true;
    console.log('connectoverlay' + this.connectoverlay[index]);
    console.log('closecard' + this.closecard[index]);
  }

}
