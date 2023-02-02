import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { FormComponent } from 'src/app/components/form/form.component';
import { FormControlComponent } from 'src/app/components/form-control/form-control.component';



@NgModule({
  declarations: [
    LoginComponent,
    ChangePasswordComponent
  ],
  imports: [
    CommonModule,
    FormComponent,
    FormControlComponent
  ],
  exports: [
    LoginComponent,
    ChangePasswordComponent
  ]
})
export class AccountModule { }
