import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { OverlayContainer } from '@angular/cdk/overlay';
import { personal} from '../personal';
import { ProfileService } from '../profile.service';
import { RouterLink, Router } from '@angular/router';
import { UserProfileService } from '../user-profile.service';
import * as moment from 'moment';
import { trigger, transition, keyframes, animate } from '@angular/animations';
import * as kf from './keyframes';
import { VideoComponent } from '../video/video.component';
import { MatDialog } from '@angular/material';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';




export interface Religion {
  value: string;
  viewValue: string;
}

export interface Gender {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
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
export class ProfileComponent implements OnInit {

  //Min and Max Date
  minDate = new Date(1920, 0, 1);
  maxDate = new Date(2002, 0, 1);


  openVideo() {
    const dialogRef = this.dialog.open(VideoComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.Obj.videoUrl = result;
      this.vidUrl = result;
    });
  }
 


  // tslint:disable-next-line: variable-name
  constructor(private dialog: MatDialog,private _formBuilder: FormBuilder, overlayContainer: OverlayContainer,
              private profileservice: ProfileService, private userProfileService: UserProfileService, private router: Router) {
    overlayContainer.getContainerElement().classList.add('unicorn-dark-theme');
  }


  /**************************8
   * ANIMATION
   ***********************/

  animationState: string;



  /*-------------------------
        Personal data
  -------------------------*/

 post: any;
 progress = 10;


  private basicPersonalDetails;
  private habitsDetails;
  private familyDetails;
  private educationalDetails;
  private professionalDetails;
  private horoscopeDetails;
  private Obj: {[k: string]: any} = {};


  startstatus = true;

  status: boolean;
  status1 = false;
  status2 = false;
  status3 = false;
  status4 = false;
  status5 = false;
  statusf = false;

  stage1 = false;
  stage2 = false;
  stage3 = false;
  stage4 = false;
  stage5 = false;
  stage6 = false;
  side = false;

  opened = true;

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  fourthFormGroup: FormGroup;
  fifthFormGroup: FormGroup;
  familyFormGroup: FormGroup;
  personalFormGroup: FormGroup;
  private route = false;




  /*Religion*/
  // religion: Religion[] = [
  //   {value: 'Hindu', viewValue: 'Hindu'},
  //   {value: 'Muslim', viewValue: 'Muslim'},
  //   {value: 'Christian', viewValue: 'Christian'}
  // ];

  options: string[] = [
    'Hindu', 'Christian', 'Muslim'
  ];

  gender: Gender[] = [
    {value: 'Male', viewValue: 'Male'},
    {value: 'Female', viewValue: 'Female'}
  ];

  startAnimation(state) {
    console.log(state);
    if (!this.animationState) {
      this.animationState = state;
    }
  }

  resetAnimationState() {
    this.animationState = '';
  }

  eid:string;
  // religion = new FormControl();
  
  filteredOptions: Observable<string[]>;

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }

  ngOnInit() {
    
    console.log(this.filteredOptions);
    this.userProfileService.fillProfileDetails().subscribe(data => {
      console.log(data);

      if (data != null) {
        console.log('Go To DashBoard');

        this.router.navigate(['dashboard']);
      }

    }
    );
    this.side = true;
    this.blockstatus();
    this.createForm();
    this.filteredOptions = this.firstFormGroup.controls['religion'].valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
    console.log(this.thirdFormGroup.value);
    console.log(this.fourthFormGroup.value);
    console.log(this.fifthFormGroup.value);
    console.log(this.familyFormGroup);
    this.eid=localStorage.getItem('emailid');
  }


  blockstatus() {
      this.progress = 20;
      this.status = true;
      this.status1 = false;
      this.status2 = false;
      this.status3 = false;
      this.status4 = false;
      this.statusf = false;
      this.startstatus = false;
      this.status5 = false;
  }

  blockstatus1() {
    this.progress = 35;
    setTimeout(() => {
      this.status = false;
      this.status1 = true;
      this.status2 = false;
      this.status3 = false;
      this.status4 = false;
      this.statusf = false;
      this.status5 = false;
    }, 900);

}

blockstatus2() {
  this.progress = 60;
  setTimeout(() => {
  this.status = false;
  this.status1 = false;
  this.status2 = true;
  this.status3 = false;
  this.status4 = false;
  this.statusf = false;
  this.status5 = false;
  }, 900);
}

blockstatus3() {
  this.progress = 80;
  setTimeout(() => {
  this.status = false;
  this.status1 = false;
  this.status2 = false;
  this.status3 = true;
  this.status4 = false;
  this.statusf = false;
  this.status5 = false;
  }, 900);
}

blockstatus4() {
  this.progress = 90;
  setTimeout(() => {
  this.status = false;
  this.status1 = false;
  this.status2 = false;
  this.status3 = false;
  this.status4 = true;
  this.statusf = false;
  this.status5 = false;
  }, 900);
}

blockstatusf() {
  this.progress = 45;
  setTimeout(() => {
  this.status = false;
  this.status1 = false;
  this.status2 = false;
  this.status3 = false;
  this.status4 = false;
  this.statusf = true;
  this.status5 = false;
}, 900);
}

blockstatus5() {
  this.progress = 100;
  setTimeout(() => {
  this.status = false;
  this.status1 = false;
  this.status2 = false;
  this.status3 = false;
  this.status4 = false;
  this.statusf = false;
  this.status5 = true;
}, 900);
}



createForm() {
  const emailregex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  const MOBILE_PATTERN: RegExp = /^[6-9]\d{9}$/;
  const myreg = /(^|\s)((https?:\/\/)?[\w-]+(\.[\w-]+)+\.?(:\d+)?(\/\S*)?)/gi;



  // this.personalFormGroup = this._formBuilder.group({
  //   'firstname': [''],
  //   'lastname': [''],
  //   'gender': [''],
  //   'city': [''],
  //   'nativeCity': [''],
  //   'tongue': [''],
  //   'phone': ['', [Validators.pattern(MOBILE_PATTERN),Validators.minLength(10),Validators.maxLength(10)]],
  //   'email': ['', Validators.pattern(emailregex)],
  //   'religion': [''],
  //    'height':[''],
  //    'weight':[''],
  //    'hobbies':[''],
  //    'interests':[''],
  //    'diet':[''],
  //    'drink':[''],
  //    'smoke':[''],
  //    'marital':[''],
  //    'challenged':[''],
  //    'abroad':[''],
  //    'yourself':[''],
  //   'profession':[''],
  //   'income':[''],
  //   'current company':[''],
  //   'experience':[''],
  //   'linkedin':['',Validators.pattern(myreg)],
  //   'location':['']
  // })







  this.firstFormGroup = this._formBuilder.group({
    firstName: [''],
    lastName: [''],
    gender: [''],
    age : [''],
    city: [''],
    nativeCity: [''],
    tongue: [''],
    phone: ['', [Validators.pattern(MOBILE_PATTERN), Validators.minLength(10), Validators.maxLength(10)]],
    email: ['', Validators.pattern(emailregex)],
    religion: ['']
   });

  this.secondFormGroup = this._formBuilder.group({
     height: [''],
     weight: [''],
     hobbies: [''],
     interests: [''],
     diet: [''],
     drink: [''],
     smoke: [''],
     marital: [''],
     challenged: [''],
     abroad: [''],
     yourself: [''],
   });

  this.thirdFormGroup = this._formBuilder.group({
    profession: [''],
    income: [''],
    currentCompany: [''],
    experience: [''],
    linkedin: ['', Validators.pattern(myreg)],
    location: ['']
  });

  this.fourthFormGroup = this._formBuilder.group({
    education: [''],
    college: [''],
    city: [''],
    passingYear: [''],
  });

  this.fifthFormGroup = this._formBuilder.group({
    timeOfBirth: [''],
    placeOfBirth: [''],
    dateOfBirth: [''],
    manglik: ['']
  });

  this.familyFormGroup = this._formBuilder.group({
    members: [''],
    fatherName: [''],
    fatherOccupation: [''],
    motherName: [''],
    motherOccupation: [''],
    youngb: [''],
    youngs: [''],
    elderb: [''],
    elders: [''],
    aboutf: ['']
  });

  }


  submit(post) {
    this.post = post;
    console.log(post);
    }

  stage1complete() {
    this.stage1 = true;
    console.log(this.stage1);
    }

  stage2complete() {
    this.stage2 = true;
    console.log(this.stage2);
    }

  stage3complete() {
    this.stage3 = true;
    console.log(this.stage3);
    }

  stage4complete() {
    this.stage4 = true;
    console.log(this.stage4);
    }


  stage5complete() {
    this.stage5 = true;
    console.log(this.stage5);
    }

  stage6complete() {
    this.stage6 = true;
    console.log(this.stage6);
    }


  openside() {
    this.side = !(this.side);
  }
  getErrorEmail() {
    return this.firstFormGroup.get('email').hasError('pattern') ? 'Not a valid emailaddress' : '';
  }
  getErrorPhone() {
    return this.firstFormGroup.get('phone').hasError('pattern') ? 'Not a valid phone Number' :
      this.firstFormGroup.get('phone').hasError('minLength') ? 'Must be 10 chars' :
      this.firstFormGroup.get('phone').hasError('maxLength') ? 'Not more than 10 chars' : '';

  }
  getUrlError() {
    return 'Not a valid Url';
  }

  // hidestart()
  // {
  //   this.startstatus=!this.startstatus;
  // }



  addToRegistryPersonal() {
    this.fifthFormGroup.controls['dateOfBirth'].setValue(this.firstFormGroup.controls['age'].value);
    
    const per: personal = new personal();
    this.firstFormGroup.value.age = moment().diff(this.firstFormGroup.value.age, 'years');
    per.basic = this.firstFormGroup.value;
    per.habits = this.secondFormGroup.value;
    per.family = this.familyFormGroup.value;
    this.profileservice.addPersonal(per)
                    .subscribe(
                      console.log(per)
                    );
    this.Obj.basicPersonalDetails = per.basic;
    this.Obj.habitsDetails = per.habits;
    this.Obj.familyDetails = per.family;
    this.Obj.hasProfessionalDetails = false;
    this.Obj.hasEducationalDetails = false;
    this.Obj.hasHoroscopeDetails = false;
    this.Obj.deactivated = false;
    console.log(this.Obj);

  }

  addToRegistryProfessional() {
    // this.profileservice.addProfessional(this.thirdFormGroup.value)
    //                 .subscribe(
    //                   console.log(this.thirdFormGroup.value)
    //                 );
    this.Obj.professionalDetails = this.thirdFormGroup.value;
    this.Obj.hasProfessionalDetails = true;
    console.log(this.Obj);

  }

  addToRegistryEducation() {
    this.profileservice.addEducation(this.thirdFormGroup.value, this.fourthFormGroup.value)
                    .subscribe(
                      console.log(this.fourthFormGroup.value)
                    );
    this.Obj.educationalDetails = this.fourthFormGroup.value;
    this.Obj.hasEducationalDetails = true;
    console.log(this.Obj);
  }

  addToRegistryHoroscope() {

    this.profileservice.addHoroscope(this.fifthFormGroup.value)
                    .subscribe(
                      console.log(this.fifthFormGroup.value)
                    );
    this.Obj.horoscopeDetails = this.fifthFormGroup.value;
    this.Obj.hasHoroscopeDetails = true;
    console.log(this.Obj);
  }


  SentToProfile() {
    localStorage.setItem('gender', this.firstFormGroup.value.gender);
    console.log(this.Obj);
    this.profileservice.SentObjBackEnd(this.Obj).subscribe(data => {console.log(data); }
    );
  }

  logout() {
    localStorage.clear();
  }

  //                File Upload
  url;
  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]); // read file as data url
      const fileToUpload = event.target.files[0];
      console.log(fileToUpload);
      // fileToUpload.name = this.BasicPersonalthis.Obj.imageUrlDetails.firstName + "_dp.jpg";

      reader.onload = (event) => { // called once readAsDataURL is completed
        // tslint:disable-next-line: no-string-literal
        this.url = event.target['result'];
        console.log(this.url);
        
      }
      var i=0;
      if(this.Obj.imageUrl){
        i=this.Obj.imageUrl.length;
      }else{
        this.Obj.imageUrl = [];
      }
      this.userProfileService.SentFileToS3(fileToUpload,"image",i,"webp").subscribe(data => { 
        this.Obj.imageUrl.unshift(data);
        this.url=data;
        console.log(this.Obj);
      },
      error => {
        console.log(error);

      }
      );
    }
  }
  vidUrl;
  onSelectFileVideo(event) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url
      const fileToUpload = event.target.files[0];
      reader.onload = (event) => { // called once readAsDataURL is completed
        // tslint:disable-next-line: no-string-literal
        this.vidUrl = event.target['result'];
        console.log(this.vidUrl);
        
      }
      this.userProfileService.SentFileToS3(fileToUpload,"video",0,"mp4").subscribe(data => { 
        this.Obj.videoUrl = data;
        this.vidUrl = data;
        console.log(this.Obj);
      },
      error => {
        console.log(error);

      }
      );
    }
  }

}
