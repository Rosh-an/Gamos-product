import { Component, OnInit } from '@angular/core';
import { AuthService } from 'angularx-social-login';
import { FacebookLoginProvider, GoogleLoginProvider } from 'angularx-social-login';
import {SocialUser} from 'angularx-social-login';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import { UserProfileService } from '../user-profile.service';
import { RegisterComponent } from './register/register.component';
import { LoginRegisterService } from './login-register.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private route: Router, private formBuilder: FormBuilder, private authService: AuthService, public dialog: MatDialog,
              private loginRegisterService: LoginRegisterService,
              private userProfileService: UserProfileService) { }

  error: any = '';
  formGroup: FormGroup;
  post: any = '';
  responseObj;
  private user: SocialUser;
  private loggedIn: boolean;
  startsession: boolean;
  toDashboard = false;

  showfalse: boolean;
  // submit(post){
  //   post.loginType='general';
  //   this.submit1(post);
  // }

  load: boolean;
  showlog: boolean;

  ngOnInit() {

    localStorage.clear();
    this.createForm();
    this.startsession = false;
    /*-----LOCAL STORAGE------*/
    localStorage.setItem('Test', 'hello');
    this.showfalse = false;
    this.load = false;
    this.showlog = false;
  }

  createForm() {
  this.formGroup = this.formBuilder.group({
    userEmail: [''],
    password: [''],
    loginType: ['']
   });
}

  submit(post) {
    this.showlog = false;
    this.load = true;
    if (post.loginType !== 'google' && post.loginType !== 'facebook') {
      post.loginType = 'general';
    }
    this.post = post;
    this.loginRegisterService.Login(post).subscribe(
        data => {
          console.log(this.startsession);
          console.log(data, 'Token in Login Component');
          this.responseObj = data;
            // this.post.userEmail = this.responseObj.token;
          console.log(this.responseObj.token, 'Token in Login Component');
          if ((this.responseObj.token) !== 'Login failed wrong Password' && (this.responseObj.token) !== 'Login failed check Email') {
              this.startsession = true;
              localStorage.setItem('emailid', post.userEmail);
              localStorage.setItem('token', this.responseObj.token);
              this.userProfileService.fillProfileDetails().subscribe(
              // tslint:disable-next-line: no-shadowed-variable
              data => {
                console.log(data);
                if (data == null) {
                  this.toDashboard = false;
                } else {
                  this.toDashboard = true;
                }
                console.log(this.toDashboard);

              }
              );

              this.dialog.closeAll();
              if (this.toDashboard === true) {this.route.navigateByUrl('/dashboard'); } else {this.route.navigateByUrl('/profile'); }

            } else {
              this.showfalse = true;
            }
          console.log(this.startsession);
          }
      );
    setTimeout(() => {
        this.load =  false;
        this.showlog = true;
      }, 1500);

    }
    socialLogin(obj) {

    }


    signInWithGoogle(post): void {
      this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
      this.authService.authState.subscribe((user) => {
        // this.user = user;
        if (user != null) {
        post.userEmail = user.email;
        post.loginType = 'google';
        post.password = '';
        console.log('Sending' + post);
        console.log('User' + user);
        this.submit(post);
        this.loggedIn = (user != null); }
      });
    }

    signInWithFB(post): void {
      this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
      this.authService.authState.subscribe((user) => {
        if (user != null) {
        post.userEmail = user.email;
        post.loginType = 'facebook';
        post.password = '';
        this.submit(post);
        this.loggedIn = (user != null);
        }
      });
    }

    signOut(): void {
      this.authService.signOut();
    }

    openRegister() {
      this.dialog.open(RegisterComponent);
    }

    openlogin() {
      this.dialog.open(LoginComponent);
    }

    loginreport() {
    }

  }
