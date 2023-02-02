import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { SpecialsComponent } from './specials/specials.component';
import { JumbotronComponent } from '../../components/jumbotron/jumbotron.component';
import { ThumbnailComponent } from '../../components/thumbnail/thumbnail.component';
import { FormComponent } from '../../components/form/form.component';
import { SpecialComponent } from '../../components/special/special.component';
import { FormControlComponent } from 'src/app/components/form-control/form-control.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
      ContactComponent,
      HomeComponent,
      SpecialsComponent,
      LoginComponent
  ],
  imports: [
      CommonModule,
      FormsModule,
      JumbotronComponent,
      ThumbnailComponent,
      FormComponent,
      FormControlComponent,
      SpecialComponent
  ],
  exports: [
    ContactComponent,
    HomeComponent,
    SpecialsComponent
  ]
})
export class HomeModule { }
