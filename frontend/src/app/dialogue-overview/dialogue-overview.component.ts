import { Component, OnInit } from '@angular/core';
import { Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface DialogData {
  animal: string;
  name: string;
}


@Component({
  selector: 'app-dialogue-overview',
  templateUrl: './dialogue-overview.component.html',
  styleUrls: ['./dialogue-overview.component.css']
})
export class DialogueOverviewComponent implements OnInit {

  // constructor(public dialogRef: MatDialogRef<DialogueOverviewComponent>,
  //   @Inject(MAT_DIALOG_DATA) public data: DialogData)  { }

  subscription: string;

  ngOnInit() {
  }

}
