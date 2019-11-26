import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MustMatch } from './register.validator';
import { LoginRegisterService } from '../login-register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide = true;
  formGroup: FormGroup;
  private responseObj;
  titleAlert = 'This field is required';
  post: any = '';


  constructor(private formBuilder: FormBuilder, private loginRegisterService: LoginRegisterService) { }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    const emailregex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    this.formGroup = this.formBuilder.group({
      userEmail: ['', [Validators.required, Validators.pattern(emailregex)], this.checkInUseEmail],
      password: ['', [Validators.required, this.checkPassword]],
      confirmPassword: ['', Validators.required]
     },
     {
      validator: MustMatch('password', 'confirmPassword')
     });

  }

  get f() { return this.formGroup.controls; }

  checkPassword(control) {
    const enteredPassword = control.value;
    const passwordCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
    return (!passwordCheck.test(enteredPassword) && enteredPassword) ? { requirements: true } : null;
  }

  checkInUseEmail(control) {
    // mimic http database access
    const db = ['tony@gmail.com'];
    return new Observable(observer => {
      setTimeout(() => {
        const result = (db.indexOf(control.value) !== -1) ? { alreadyInUse: true } : null;
        observer.next(result);
        observer.complete();
      }, 4000);
    });
  }

  getErrorEmail() {
    return this.formGroup.get('userEmail').hasError('required') ? 'Field is required' :
      this.formGroup.get('userEmail').hasError('pattern') ? 'Not a valid emailaddress' :
        this.formGroup.get('userEmail').hasError('alreadyInUse') ? 'This emailaddress is already in use' : '';
  }

  getErrorPassword() {
    return this.formGroup.get('password').hasError('required') ? 'Field is required (at least eight characters, one uppercase letter and one number)' :
      this.formGroup.get('password').hasError('requirements') ? 'Password needs to be at least eight characters, one uppercase letter and one number' : '';
  }



  onSubmit(post) {
    console.log(post, 'Inside onSubmit');

    this.post = post;
    this.loginRegisterService.Register(this.post).subscribe(
      data => {
            console.log(data, 'Inside register Component');
        }
    );
  }

}
