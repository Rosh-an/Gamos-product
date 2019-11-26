import { ParamProfileService } from './param-profile.service';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

import { ApiAiClient } from 'api-ai-javascript/es6/ApiAiClient';

import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
export class Message {
    constructor(public content: string, public sentBy: string) {}
  }

@Injectable()
export class SearchService {
    readonly token = environment.dialogflow.angularBot;
    readonly client = new ApiAiClient({ accessToken: this.token });
    constructor(private param: ParamProfileService) {}
    objToSearch: any;

    conversation = new BehaviorSubject<Message[]>([]);
    setSentObj(obj) {
      console.log(obj);
      this.objToSearch = obj;
    }
    getObj() {
      return this.objToSearch;
    }


}
