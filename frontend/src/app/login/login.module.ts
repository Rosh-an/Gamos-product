import { MaterialModule } from './../material/material.module';
import { RegisterComponent } from './register/register.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { SignComponent } from './sign.component';
import { LoginRoutingModule } from './login-routing.module';


@NgModule({
  declarations: [RegisterComponent, SignComponent, LoginComponent],
  imports: [
    CommonModule,
    MaterialModule,
    LoginRoutingModule,
  ],
  exports: [LoginRoutingModule],
  entryComponents:[LoginComponent, RegisterComponent]
})
export class LoginModule { }
