import { Component, OnInit, ElementRef } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { HostBinding, NgModule, HostListener, AfterViewInit, Input} from '@angular/core';

import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';

import { DialogueOverviewComponent } from '../dialogue-overview/dialogue-overview.component';
import { TruncateTextPipe } from '../truncate-text.pipe';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('fade',
    [
      state('void', style({ opacity : 0})),
      transition(':enter', [ animate(300)]),
      transition(':leave', [ animate(400)]),
    ],
),
trigger('fade1',
    [
      state('void', style({ opacity : 0})),
      transition(':enter', [ animate(2500)]),
      transition(':leave', [ animate(3000)]),
    ], ) ,
    trigger('scrollAnimation', [
      state('show', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      state('hide',   style({
        opacity: 0,
        transform: 'translateX(-100%)'
      })),
      transition('show => hide', animate('700ms ease-out')),
      transition('hide => show', animate('700ms ease-in'))
    ])]
})
export class HomeComponent implements OnInit {



  state = 'hide';
  title = 'gamos-frontend';
  subscription: string;
  stories: string;
  showstory: boolean;
  CAROUSEL_BREAKPOINT1 = 1086;
  CAROUSEL_BREAKPOINT2 = 880;
  CAROUSEL_BREAKPOINT3 = 680;
  CAROUSEL_BREAKPOINT4 = 467;
  carouselDisplayMode = 'three';
  public slidesList = new Array<never>(3);



  i: any;

  ngOnInit() {
    this.stories = 'Marriages are made in heaven, but my marriage was made possible through  Elite Matrimony! I am a businessman committed to my work. Elite Matrimony  found a life partner that my parents approved of. The Relationship Manager, Mr Soheb, showed me a few profiles and that\'s when I saw Tanu, a dentist. When we finally met, sparks flew. She met my parents and they liked her too.  Our marriage is going to happen very soon.';
    this.showstory = true;
    this.i = 1;

  }








  constructor(public el: ElementRef, public dialog: MatDialog, private breakpointObserver: BreakpointObserver) {}
  /** Based on the screen size, switch from standard to one column per row */
  // cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
  //   map(({ matches }) => {
  //     if (matches) {
  //       return [
  //         { title: 'Card 1', cols: 1, rows: 1 },
  //         { title: 'Card 2', cols: 1, rows: 1 },
  //         { title: 'Card 3', cols: 1, rows: 1 },
  //       ];
  //     }

  //     return [
  //       { title: 'Card 1', cols: 1, rows: 1 },
  //       { title: 'Card 2', cols: 1, rows: 1 },
  //       { title: 'Card 3', cols: 1, rows: 1 },
  //     ];
  //   })
  // );


  @HostListener('window:scroll', [])
  checkScroll() {
    const componentPosition = this.el.nativeElement.offsetTop;
    const scrollPosition = window.pageYOffset;

    if (scrollPosition >= componentPosition) {
      this.state = 'show';
    } else {
      this.state = 'hide';
    }

  }

  // openDialog() {
  //   this.dialog.open(LoginComponent);
  // }

  openDialog1() {
    this.dialog.open(DialogueOverviewComponent);
  }



  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //     // this.animal = result;
  //   });
  // }


  // spliting the cards based on window size
  @HostListener('window:resize')
  onWindowResize() {
    if (window.innerWidth >= this.CAROUSEL_BREAKPOINT4 && window.innerWidth <= this.CAROUSEL_BREAKPOINT3) {
      this.carouselDisplayMode = 'two';
      this.showstory = false;
      console.log(this.showstory);
    } else if (window.innerWidth <= this.CAROUSEL_BREAKPOINT4) {
      this.carouselDisplayMode = 'one';
      this.showstory = false;
      console.log(this.showstory);
    } else {
      this.carouselDisplayMode = 'three';
      this.showstory = true;
      console.log(this.showstory);
    }
  }
}




