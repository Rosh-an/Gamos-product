import { Component, OnInit, HostListener, ElementRef } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';

@Component({
  selector: 'app-top-profile',
  templateUrl: './top-profile.component.html',
  styleUrls: ['./top-profile.component.scss'],
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
    ], ),
    trigger('scrollAnimation', [
      state('show', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      state('hide',  style({
        opacity: 0,
        transform: 'translateX(-100%)'
      })),
      transition('show => hide', animate('700ms')),
      transition('hide => show', animate('700ms'))
    ])
  ]
})
export class TopProfileComponent implements OnInit {

  state = 'hide';

  constructor(public el: ElementRef) { }

  ngOnInit() {
  }

  @HostListener('window:scroll', [])
  checkScroll() {
    const componentPosition = this.el.nativeElement.offsetTop;
    const scrollPosition = window.pageYOffset;

    if (scrollPosition >= componentPosition - 300) {
      this.state = 'show';
    } else {
      this.state = 'hide';
    }

  }

}
