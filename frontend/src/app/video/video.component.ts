import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import * as RecordRTC from 'recordrtc';
import { VideoService } from '../video.service';
import { UserProfileService } from '../user-profile.service';
import { UserProfileComponent } from '../user-profile/user-profile.component';

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit {

  constructor(private userProfileService: UserProfileService) { }

  @ViewChild('videoElement', { static: false })
  videoElement: ElementRef;
  video: any;
  recorder: any;
  blob: any;
  msg: string;
  vidUrl = '';

  // tslint:disable-next-line: use-lifecycle-interface
  ngAfterViewInit() {
    this.video = this.videoElement.nativeElement;
  }

  ngOnInit() {
    // this.video = document.querySelector('video');
  }

  start() {
    const browser: any = navigator;

    browser.getUserMedia = (browser.getUserMedia ||
      browser.webkitGetUserMedia ||
      browser.mozGetUserMedia ||
      browser.msGetUserMedia);

    browser.mediaDevices.getUserMedia({ audio: true, video: true }).then(stream => {
      this.video.muted = true;
      this.video.volume = 0;
      this.video.srcObject = stream;
      this.recorder = RecordRTC(stream, {
        type: 'video'
      });
      this.recorder.setRecordingDuration(60000, () => {
        this.video.src = null;
        this.video.srcObject = null;
        this.video.muted = false;
        this.video.volume = 1;
        this.blob = this.recorder.getBlob();
        console.log(this.blob);
        this.video.src = URL.createObjectURL(this.recorder.getBlob());
        this.recorder.camera.stop();
        this.recorder.destroy();
        this.recorder = null;
        this.userProfileService.SentFileToS3(this.blob, 'video', 0, 'mp4').subscribe(res => {
          this.vidUrl = res;
          console.log(this.vidUrl);
        });
      });
      this.recorder.startRecording();
      // release camera on stopRecording
      this.recorder.camera = stream;
    });
  }


  stop() {
    this.recorder.stopRecording(() => {
      this.video.src = null;
      this.video.srcObject = null;
      this.video.muted = false;
      this.video.volume = 1;
      this.blob = this.recorder.getBlob();
      console.log(this.blob);
      this.video.src = URL.createObjectURL(this.recorder.getBlob());
      this.recorder.camera.stop();
      this.recorder.destroy();
      this.recorder = null;
      this.userProfileService.SentFileToS3(this.blob, 'video', 0, 'mp4').subscribe(res => {
        this.vidUrl = res;
        console.log(this.vidUrl);
      });
    });
    console.log(this.vidUrl);
  }
  public blobToFile = (theBlob: Blob, fileName: string): File => {
    const b: any = theBlob;
    // A Blob() is almost a File() - it's just missing the two properties below which we will add
    b.lastModifiedDate = new Date();
    b.name = fileName;

    // Cast to a File() type
    return theBlob as File;
  }
}
