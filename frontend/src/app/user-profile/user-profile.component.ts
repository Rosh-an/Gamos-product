import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserProfileService } from '../user-profile.service';
import { string, any } from 'prop-types';
import { MatDialog } from '@angular/material';
import { VideoComponent } from '../video/video.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  likedby:number;
  connections:number;



  toBeEdited;
  genFormGroup: FormGroup;
  personalFormGroup: FormGroup;
  familyFormGroup: FormGroup;
  careerFormGroup: FormGroup;
  educationFormGroup: FormGroup;
  horoscopeFormGroup: FormGroup;
  // tslint:disable-next-line: variable-name
  constructor(private dialog: MatDialog, private _formBuilder: FormBuilder, private userProfileService: UserProfileService) { }

  url = '';
  vidUrl = '';
  fname = 'febin';
  BasicPersonalDetails: any;
  basicKeys;
  HabitsDetails;
  habitKeys;
  FamilyDetails;
  familyKeys;
  EducationalDetails;
  educationalKeys;
  ProfessionalDetails;
  professionalKeys;
  HoroscopeDetails;
  horoscopeKeys;
  private Obj: any;


  logout() {
    localStorage.clear();
  }

  ngOnInit() {
    this.userProfileService.fillProfileDetails().subscribe(
      data => {
            this.Obj = data;
            console.log(this.Obj);
            console.log(this.Obj.liked_by_profiles.length);
            this.likedby=this.Obj.liked_by_profiles.length;
            console.log(this.likedby);
            this.connections=this.Obj.accepted_requests.length;
            console.log(this.connections);
            this.BasicPersonalDetails = this.Obj.basicPersonalDetails;
            console.log(this.BasicPersonalDetails);
            localStorage.setItem('gender', this.BasicPersonalDetails.gender);
            this.fname = this.Obj.basicPersonalDetails.firstName;
            console.log(this.BasicPersonalDetails);
            this.basicKeys = Object.keys(this.BasicPersonalDetails);
            console.log(this.basicKeys);
            if (this.Obj.imageUrl) {
              this.url = this.Obj.imageUrl[0];
            } else {
              this.Obj.imageUrl = [];
            }
            if (this.Obj.videoUrl) {
              this.vidUrl = this.Obj.videoUrl;
            }
            this.HabitsDetails = this.Obj.habitsDetails;
            if (this.HabitsDetails != null || this.HabitsDetails !== undefined) {
              this.habitKeys = Object.keys(this.HabitsDetails);
            } else {
              this.HabitsDetails = {
                  height: null,
                  weight: null,
                  hobbies: null,
                  interests: null,
                  diet: null,
                  drink: null,
                  smoke: null,
                  marital: null,
                  challenged: null,
                  abroad: null,
                  yourself: null
              };
              this.habitKeys = Object.keys(this.HabitsDetails);
            }
            this.FamilyDetails = this.Obj.familyDetails;
            if (this.FamilyDetails != null || this.FamilyDetails !== undefined) {
              this.familyKeys = Object.keys(this.FamilyDetails);
            } else {
              this.FamilyDetails = {
                fatherName: null,
                fatherOccupation: null,
                motherName: null,
                motherOccupation: null,
                youngb: null,
                youngs: null,
                elderb: null,
                elders: null,
                aboutf: null
              };
              this.familyKeys = Object.keys(this.FamilyDetails);
            }
            this.EducationalDetails = this.Obj.educationalDetails;
            if (this.EducationalDetails != null || this.EducationalDetails !== undefined) {
              this.educationalKeys = Object.keys(this.EducationalDetails);
            } else {
              this.EducationalDetails = {
                  education: null,
                  college: null,
                  city: null,
                  passingYear: null
              };
              this.educationalKeys = Object.keys(this.EducationalDetails);
            }
            this.ProfessionalDetails = this.Obj.professionalDetails;
            if (this.ProfessionalDetails != null || this.ProfessionalDetails != undefined) {
              this.professionalKeys = Object.keys(this.ProfessionalDetails);
            } else {
              this.ProfessionalDetails = {
                  profession: null,
                  income: null,
                  currentCompany: null,
                  experience: null,
                  linkedin: null,
                  location: null
              };
              this.professionalKeys = Object.keys(this.ProfessionalDetails);
            }
            this.HoroscopeDetails = this.Obj.horoscopeDetails;
            this.HoroscopeDetails.dateOfBirth=this.HoroscopeDetails.dateOfBirth.substring(0,10);
            if (this.HoroscopeDetails != null || this.HoroscopeDetails != undefined) {
              this.horoscopeKeys = Object.keys(this.HoroscopeDetails);
              
            } else {
              this.HoroscopeDetails = {
                timeOfBirth: null,
                placeOfBirth: null,
                dateOfBirth: null,
                manglik: null
              };
              this.horoscopeKeys = Object.keys(this.HoroscopeDetails);
            }
      }
    );
  }
  openVideo() {
    const dialogRef = this.dialog.open(VideoComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.Obj.videoUrl = result;
      this.vidUrl = result;
      this.EditTheObject(this.Obj);
    });
    this.ngOnInit();
  }
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
      i=this.Obj.imageUrl.length;
      this.userProfileService.SentFileToS3(fileToUpload,"image",i,"webp").subscribe(data => { 
        this.Obj.imageUrl.unshift(data);
        this.url=data;
        console.log(this.Obj);

        this.EditTheObject(this.Obj);
      },
      error => {
        console.log(error);

      }
      );
    }
  }
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

        this.EditTheObject(this.Obj);
      },
      error => {
        console.log(error);

      }
      );
    }
  }
  doEdit(val) {
    this.createForm();
    this.toBeEdited = val;
  }
  EditTheObject(object) {
    this.userProfileService.Edit(this.Obj).subscribe(
      data => {
        this.Obj = data;
        console.log(this.Obj);
        
      }
    );
  }
  Edited() {
    if (this.toBeEdited === 'general') {
      console.log(this.genFormGroup.value);
      console.log(this.Obj);
      this.Obj.basicPersonalDetails = this.genFormGroup.value;
      this.BasicPersonalDetails = this.genFormGroup.value;
      this.EditTheObject(this.Obj);

    } else if (this.toBeEdited === 'personal') {
      console.log(this.personalFormGroup.value);
      console.log(this.Obj);
      this.Obj.habitsDetails = this.personalFormGroup.value;
      this.HabitsDetails = this.personalFormGroup.value;
      this.EditTheObject(this.Obj);
    } else if (this.toBeEdited === 'family') {
      console.log(this.familyFormGroup.value);
      console.log(this.Obj);
      this.Obj.familyDetails = this.familyFormGroup.value;
      this.FamilyDetails = this.familyFormGroup.value;
      this.EditTheObject(this.Obj);
    } else if (this.toBeEdited === 'career') {
      console.log(this.careerFormGroup.value);
      console.log(this.Obj);
      this.Obj.professionalDetails = this.careerFormGroup.value;
      this.Obj.hasProfessionalDetails = true;
      this.ProfessionalDetails = this.careerFormGroup.value;
      this.EditTheObject(this.Obj);
    } else if (this.toBeEdited === 'education') {
      console.log(this.educationFormGroup.value);
      console.log(this.Obj);
      this.Obj.educationalDetails = this.educationFormGroup.value;
      this.Obj.hasEducationalDetails = true;
      this.EducationalDetails = this.educationFormGroup.value;
      this.EditTheObject(this.Obj);
    } else if (this.toBeEdited === 'horoscope') {
      console.log(this.horoscopeFormGroup.value);
      console.log(this.Obj);
      this.Obj.horoscopeDetails = this.horoscopeFormGroup.value;
      this.Obj.hasHoroscopeDetails = true;
      this.HoroscopeDetails = this.horoscopeFormGroup.value;
      this.EditTheObject(this.Obj);
    }
    this.toBeEdited = '';
  }
  createForm() {
    const emailregex: RegExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const MOBILE_PATTERN: RegExp = /^[6-9]\d{9}$/;
    const myreg = /(^|\s)((https?:\/\/)?[\w-]+(\.[\w-]+)+\.?(:\d+)?(\/\S*)?)/gi;

    this.genFormGroup = this._formBuilder.group({
      firstName: [this.BasicPersonalDetails.firstName],
      lastName: [this.BasicPersonalDetails.lastName],
      gender: [this.BasicPersonalDetails.gender],
      city: [this.BasicPersonalDetails.city],
      nativeCity: [this.BasicPersonalDetails.nativeCity],
      tongue: [this.BasicPersonalDetails.tongue],
      phone: [this.BasicPersonalDetails.phone, [Validators.pattern(MOBILE_PATTERN), Validators.minLength(10), Validators.maxLength(10)]],
      email: [this.BasicPersonalDetails.email, Validators.pattern(emailregex)],
      religion: [this.BasicPersonalDetails.religion],
      age: [this.BasicPersonalDetails.age]
     });
    this.personalFormGroup = this._formBuilder.group({
      height: [this.HabitsDetails.height],
      weight: [this.HabitsDetails.weight],
      hobbies: [this.HabitsDetails.hobbies],
      interests: [this.HabitsDetails.interests],
      diet: [this.HabitsDetails.diet],
      drink: [this.HabitsDetails.drink],
      smoke: [this.HabitsDetails.smoke],
      marital: [this.HabitsDetails.marital],
      challenged: [this.HabitsDetails.challenged],
      abroad: [this.HabitsDetails.abroad],
      yourself: [this.HabitsDetails.yourself],
    });

    this.careerFormGroup = this._formBuilder.group({
     profession: [this.ProfessionalDetails.profession],
     income: [this.ProfessionalDetails.income],
     currentCompany: [this.ProfessionalDetails.currentCompany],
     experience: [this.ProfessionalDetails.experience],
     linkedin: [this.ProfessionalDetails.linkedin, Validators.pattern(myreg)],
     location: [this.ProfessionalDetails.location]
   });

    this.educationFormGroup = this._formBuilder.group({
     education: [this.EducationalDetails.education],
     college: [this.EducationalDetails.college],
     city: [this.EducationalDetails.city],
     passingYear: [this.EducationalDetails.passingYear],
   });

    this.horoscopeFormGroup = this._formBuilder.group({
     timeOfBirth: [this.HoroscopeDetails.timeOfBirth],
     placeOfBirth: [this.HoroscopeDetails.placeOfBirth],
     dateOfBirth: [this.HoroscopeDetails.dateOfBirth],
     manglik: [this.HoroscopeDetails.manglik]
   });

    this.familyFormGroup = this._formBuilder.group({
     members: [this.FamilyDetails.members],
     fatherName: [this.FamilyDetails.fatherName],
     fatherOccupation: [this.FamilyDetails.fatherOccupation],
     motherName: [this.FamilyDetails.motherName],
     motherOccupation: [this.FamilyDetails.motherOccupation],
     youngb: [this.FamilyDetails.youngb],
     youngs: [this.FamilyDetails.youngs],
     elderb: [this.FamilyDetails.elderb],
     elders: [this.FamilyDetails.elders],
     aboutf: [this.FamilyDetails.aboutf]
   });

  }

}
