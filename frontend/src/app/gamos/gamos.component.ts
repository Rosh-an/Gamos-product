import { Component, OnInit, ElementRef, HostListener } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {
  trigger,
  state,
  style,
  animate,
  transition,
  // ...
} from '@angular/animations';
import { LoginComponent } from '../login/login.component';
import { DialogueOverviewComponent } from '../dialogue-overview/dialogue-overview.component';

@Component({
  selector: 'app-gamos',
  templateUrl: './gamos.component.html',
  styleUrls: ['./gamos.component.css'],
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
        transform: 'translateY(0)'
      })),
      state('hide',   style({
        opacity: 0,
        transform: 'translateY(-100%)'
      })),
      transition('show => hide', animate('700ms ease-out')),
      transition('hide => show', animate('700ms ease-in'))
    ])



  ]
})
export class GamosComponent implements OnInit {

  state = 'hide';
  smallScreen: boolean;

  constructor( public el: ElementRef, public dialog: MatDialog) { }

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

  ngOnInit() {
  }

  openDialog() {
    this.dialog.open(LoginComponent);
  }

  openDialog1() {
    this.dialog.open(DialogueOverviewComponent);
  }


}
