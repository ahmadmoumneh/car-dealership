import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { PurchaseComponent } from './purchase/purchase.component';
import { ReactiveFormsModule } from '@angular/forms';
import { VehicleDetailComponent } from 'src/app/components/vehicle-detail/vehicle-detail.component';
import { TableComponent } from 'src/app/components/table/table.component';
import { FormComponent } from 'src/app/components/form/form.component';
import { FormControlComponent } from 'src/app/components/form-control/form-control.component';
import { SearchComponent } from 'src/app/components/search/search.component';

@NgModule({
  declarations: [
    PurchaseComponent
  ],
  imports: [
    HomeComponent,
    CommonModule,
    ReactiveFormsModule,
    VehicleDetailComponent,
    TableComponent,
    FormComponent,
    FormControlComponent,
    SearchComponent
  ],
  exports: [
    HomeComponent,
    PurchaseComponent
  ]
})
export class SalesModule { }
