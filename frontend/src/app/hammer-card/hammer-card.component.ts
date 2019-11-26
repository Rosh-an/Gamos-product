import { Component, OnInit } from '@angular/core';
import { trigger, keyframes, animate, transition} from '@angular/animations';
import * as kf from './keyframes';
import { delay } from 'rxjs/operators';




@Component({
  selector: 'app-hammer-card',
  templateUrl: './hammer-card.component.html',
  styleUrls: ['./hammer-card.component.css'],
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
export class HammerCardComponent implements OnInit {

  constructor() { }

  // tslint:disable-next-line: variable-name
  card_no = 1;
  // tslint:disable-next-line: variable-name
  scard_no = '1';
  url = '../../assets/pp' + this.scard_no + '.jpg';
  animationState: string;

  c: number;

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
    this.url = '../../assets/pp' + this.scard_no + '.jpg';
    console.log(this.url);
  }

  ngOnInit() {
  }

}
