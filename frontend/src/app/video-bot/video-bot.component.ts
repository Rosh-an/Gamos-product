import { Component, OnInit } from '@angular/core';
import * as RecordRTC from 'recordrtc';
import { VideoService, Message } from '../video.service';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/scan';


@Component({
  selector: 'app-video-bot',
  templateUrl: './video-bot.component.html',
  styleUrls: ['./video-bot.component.css']
})
export class VideoBotComponent implements OnInit {

  constructor(private videoService: VideoService) { }

  video: any;
  recorder: any;
  blob: any;
  messages: Observable<Message[]>;
  formValue: string;

  ngOnInit() {
    this.video = document.querySelector('video');
    // appends to array after each new message is added to feedSource
    this.messages = this.videoService.conversation.asObservable()
        .scan((acc, val) => acc.concat(val) );
  }

  sendMessage(msg) {
    this.videoService.converse(msg);
    this.formValue = '';
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
      const data: FormData = new FormData();
      data.append('blob', this.blob);
      this.videoService.postVideoBot(data).subscribe(res => {
        console.log(res);
        this.sendMessage(res);
      });
    });
  }
}
