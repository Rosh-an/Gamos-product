import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  constructor(private http: HttpClient) { }
  i = 0;
  Edit(genObj) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json',
                                Authorization: 'Bearer ' + localStorage.getItem('token')
    })};
    const url = `https://gamos.stackroute.io:8989/profile/profile/updateProfile`;
    return this.http.put(url, genObj, httpOptions);
  }
  fillProfileDetails() {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json',
                                Authorization: 'Bearer ' + localStorage.getItem('token')
    })};
    const emailObj = {
      email: localStorage.getItem('emailid')
    };
    const url = `https://gamos.stackroute.io:8989/profile/profile/myprofile`;
    console.log(JSON.stringify(emailObj));
    
    return this.http.post(url, JSON.stringify(emailObj), httpOptions);
  }
  GetProfileDetails(email){
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type':'application/json',
                                'Authorization': 'Bearer '+localStorage.getItem('token')
    })};
    let emailObj = {
      email:email
    };
    let url = `https://gamos.stackroute.io:8989/profile/profile/myprofile`;
    console.log(JSON.stringify(emailObj));

    return this.http.post(url,JSON.stringify(emailObj),httpOptions);
  }
  SentFileToS3(fileObj,type,i,extension){
    let formData = new FormData();
    let headers =new HttpHeaders({'Authorization': 'Bearer '+localStorage.getItem('token')
    });
    const httpOptions1 = {
      headers,
      responseType: 'text'
    };
    if (type === 'video') {
      formData.append('file', fileObj, localStorage.getItem('emailid') + '-' + type + '.' + extension);

    }
    else{
      formData.append('file',fileObj,localStorage.getItem('emailid')+"-"+type+i+"."+extension);
    }
    return this.http.post(`https://gamos.stackroute.io:8989/upstream/storage/uploadFile`,formData,{
      headers,
      responseType: 'text'
    });
  }
}
