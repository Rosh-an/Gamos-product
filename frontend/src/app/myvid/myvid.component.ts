import { Component, OnInit } from '@angular/core';
import {Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-myvid',
  templateUrl: './myvid.component.html',
  styleUrls: ['./myvid.component.css']
})
export class MyvidComponent implements OnInit {

  

  constructor(public dialogRef: MatDialogRef<MyvidComponent>,
    @Inject(MAT_DIALOG_DATA) public data: URL) { }

  vidurl:any;
  showvideo:boolean;
  ngOnInit() {
   this.showvideo=true;
   console.log(this.data);
   this.vidurl=this.data;
   if((this.data)==undefined){
    this.showvideo=false;
    console.log(this.showvideo);
   }
  }

}
