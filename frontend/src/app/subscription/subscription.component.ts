import { Component, OnInit, Inject } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, OnDestroy} from '@angular/core';
import { subscribeOn } from 'rxjs/operators';
import {FormGroupDirective, NgForm} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig} from '@angular/material/dialog';

// declare var ccplan;
// declare var discontinue;

export interface Food {
  value: string;
  viewValue: string;
}

export interface Foodi {
  value: string;
  viewValue: string;
}

export interface Car {
  value: string;
  viewValue: string;
}

export interface DialogData {
  animal: string;
  name: string;
}



@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {
  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, public dialog: MatDialog) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }
  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;



  panelOpenState = false;
  password: string;


  /*******************
   * SUBSCRIPTIONS
   */



  cplan = true;
  dpan = true;
  depan = true;
  hide = true;

  current = false;

  upgrade = false;

  discontinue = false;

  deactivate = false;

  delete = false;

  modify = false;

  selectedValue: string;
  selectedCar: string;
  selectedFoodi: string;

  foods: Food[] = [

    {value: 'regular', viewValue: 'Regular'},
    {value: 'premium', viewValue: 'Premium'}
  ];

  cars: Car[] = [
    {value: 'silver', viewValue: 'Silver'},
    {value: 'gold', viewValue: 'Gold'}
  ];
  foodis: Foodi[] = [
    {value: '3months', viewValue: '3-Months'},
    {value: '6months', viewValue: '6-Months'}
  ];

  animal: string;
  name: string;

  dpanfunc() {

    this.dpan = false;

  }

  depanfunc() {

    this.depan = false;
  }

  cplanfunc() {
    this.cplan = false;
  }

  current_sub() {
    this.current = true;
    this.upgrade = false;
    this.discontinue = false;
  }

  upgrade_sub() {
    this.current = false;
    this.upgrade = true;
    this.discontinue = false;
  }

  discontinue_sub() {
    this.current = false;
    this.upgrade = false;
    this.discontinue = true;
  }

  deactivate_sub() {
    this.deactivate = true;
    this.modify = false;
  }

  delete_sub() {
    this.deactivate = false;
    this.delete = true;
    this.modify = false;
  }

  modify_sub() {
    this.deactivate = false;
    this.delete = false;
    this.modify = true;
  }

  ////////////////////// upgrade plan/////////////////////////////////////////////////////////

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      maxWidth: '350px',
      data: {selectedFoodi: this.selectedFoodi, cars: this.cars, name: this.name, animal: this.animal,
             foods: this.foods, selectedValue: this.selectedValue, selectedCar: this.selectedCar}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }

////////////// discontinue plan ////////////////////////

  openDialog2(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog2, {
      maxWidth: '350px',
      data: {name: this.name, animal: this.animal}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

    });
  }

    ngOnInit() {
  }

}


////////////////// update plan modal component logic//////////////////////////////////////



@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

    cplan = true;

  dpan = true;
  depan = true;
  hide = true;

  current = false;
  upgrade = false;
  discontinue = false;

  deactivate = false;
  delete = false;
  modify = false;


  selectedValue: string;
  selectedCar: string;
  selectedFoodi: string;

  foods: Food[] = [

    {value: 'regular', viewValue: 'Regular'},
    {value: 'premium', viewValue: 'Premium'}
  ];

  cars: Car[] = [
    {value: 'silver', viewValue: 'Silver'},
    {value: 'gold', viewValue: 'Gold'}
  ];
  foodis: Foodi[] = [
    {value: '3months', viewValue: '3-Months'},
    {value: '6months', viewValue: '6-Months'}
  ];
  dpanfunc() {
    this.dpan = false;
  }

  depanfunc() {
    this.depan = false;
  }

  cplanfunc() {
    this.cplan = false;
  }

  current_sub() {
    this.current = true;
    this.upgrade = false;
    this.discontinue = false;
  }

  upgrade_sub() {

    this.current = false;
    this.upgrade = true;
    this.discontinue = false;

  }

  discontinue_sub() {

    this.current = false;
    this.upgrade = false;
    this.discontinue = true;

  }

  deactivate_sub() {

    this.deactivate = true;
    this.delete = false;
    this.modify = false;

  }
  delete_sub() {

    this.deactivate = false;
    this.delete = true;
    this.modify = false;

  }
  modify_sub() {

    this.deactivate = false;
    this.delete = false;
    this.modify = true;

  }


  onNoClick(): void {
    this.dialogRef.close();
  }

}



// discontinue plan modal component///////////////////////////////////////////////

export interface DialogData2 {
  animal: string;
  name: string;
}



@Component({
  selector: 'dialog-overview-example-dialog2',
  templateUrl: 'dialog-overview-example-dialog2.html',
})
export class DialogOverviewExampleDialog2 {
  ccplan = false;
  discontinue = true;
  current = false;
  upgrade = false;

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog2>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData2) {}


  ccplanfunc() {
    this.ccplan = false;
    console.log('cplan', this.ccplan);
  }


  diiscontinue_sub() {

    this.current = false;
    this.upgrade = false;
    this.discontinue = true;
    console.log('kjxn', this.discontinue);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
